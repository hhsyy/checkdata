package com.yiyuclub.checkdata.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yiyuclub.checkdata.mapper.CheckdataMapper;
import com.yiyuclub.checkdata.models.Checkdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yiyu
 * @since 2020-04-27
 */
@Service
public class ICheckService {
    @Autowired
    public CheckdataMapper checkMapper;

    //获取所有数据
    public List<Checkdata> getList(String type) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("type", type);
        List<Checkdata> list = checkMapper.selectList(qw);

        return list;
    }

    //获取结果
    public String getResult(int id) {
        try {
            QueryWrapper qw = new QueryWrapper();
            qw.eq("id", id);
            Checkdata checkdata = checkMapper.selectById(id);

            String result = checkdata.getResult();

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    //获取折线图数据
    public HashMap getZxt(String type) {
        try {
            HashMap hm = new HashMap();
            double[] wd = new double[7];
            double[] sd = new double[7];
            double[] ph = new double[7];
            double[] xh = new double[7];
            QueryWrapper qw = new QueryWrapper();
            qw.eq("type",type);
            qw.orderByDesc("xh");
            List<Checkdata> list = checkMapper.selectList(qw);
            int j = 6;
            if (list != null) {
                for (Checkdata checkdata : list) {
                    wd[j] = checkdata.getTemperature();
                    sd[j] = checkdata.getHumidity();
                    ph[j] = checkdata.getPh();
                    xh[j] = checkdata.getXh();
                    j--;
                    if (j < 0) {
                        break;
                    }
                }
                hm.put("wd",wd);
                hm.put("sd",sd);
                hm.put("ph",ph);
                hm.put("xh",xh);
                return hm;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //判断
    public int checkdata(Checkdata checkdata) {
        Map<String, Boolean> map = new HashMap<>();
        int insert = 0;
        if (checkdata.getType().equals("水稻")) {
            if (checkdata.getTemperature() > 32) {
                map.put("temperature_sd_h", true);
            }

            if (checkdata.getTemperature() < 28) {
                map.put("temperature_sd_l", true);
            }

            if (checkdata.getHumidity() > 80) {
                map.put("humidity_sd_h", true);
            }

            if (checkdata.getHumidity() < 50) {
                map.put("humidity_sd_l", true);
            }

            if (checkdata.getPh() > 6) {
                map.put("ph_sd_h", true);
            }

            if (checkdata.getPh() < 5) {
                map.put("ph_sd_l", true);
            }

            if (!checkdata.getBch().equals("无")) {
                map.put("bch_sd", true);
            }

            if (!checkdata.getZc().equals("无")) {
                map.put("zc_sd", true);
            }

        } else {
            if (checkdata.getTemperature() > 35) {
                map.put("temperature_ym_h", true);
            }

            if (checkdata.getTemperature() < 28) {
                map.put("temperature_ym_l", true);
            }

            if (checkdata.getHumidity() > 90) {
                map.put("humidity_ym_h", true);
            }

            if (checkdata.getHumidity() < 70) {
                map.put("humidity_ym_l", true);
            }

            if (checkdata.getPh() > 7) {
                map.put("ph_ym_h", true);
            }

            if (checkdata.getPh() < 6.5) {
                map.put("ph_ym_l", true);
            }

            if (!checkdata.getBch().equals("无")) {
                map.put("bch_ym", true);
            }

            if (!checkdata.getZc().equals("无")) {
                map.put("zc_ym", true);
            }
        }

        try {
            if (map.isEmpty()) {
                insert = 1;
                checkdata.setResult("环境参数正常");
            } else {
                insert = 0;
                JSONObject jsonObject = JSONUtil.parseFromMap(map);
                checkdata.setResult(jsonObject.toString());
            }
            Integer xh = checkMapper.selectLastData(checkdata.getType());

            if(xh ==null){
                xh=0;
            }
            checkdata.setXh(xh + 1);
            checkMapper.insert(checkdata);
            return insert;
        } catch (Exception e) {
            return 0;
        }
    }

    public int delData(int idgroup) {
        try {
            int delete = checkMapper.deleteById(idgroup);
            return delete;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateData(Checkdata checkdata) {
        try {
            int i = checkMapper.updateById(checkdata);
            return i;
        } catch (Exception e) {
            return 0;
        }
    }

}
