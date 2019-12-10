package com.personalblog.controller;

import com.personalblog.model.Diary;
import com.personalblog.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *日记控制器
 */
@Controller
public class DiaryController {
    @Autowired
    private DiaryService diaryservice;
    //写日记
    @RequestMapping("writediary")
    public String writediary(Diary diary) {
        //获取当前日期
        Date currenttime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat();
        String dateString = formatter.format(currenttime);
        diary.setTime(dateString);
        this.diaryService.writediary(diary);
        return "Dsuccess";
    }

    /**
     * 查找全部的日记
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("selectAllDiary")
    public String selectAllDiary(HttpServletRequest request) {
        try {
            List<Diary> diarys;
            diarys = this.diaryService.selectAllDiary();
            request.setAttribute("diarys", diarys);
            return "saylist";
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 查找全部日记，但是跳转到日记管理页面
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("admindiary")
    public String selectAllDiary2(HttpServletRequest request) {
        try {
            List<Diary> diarys;
            diarys = this.diaryService.selectAllDiary();
            request.setAttribute("diarys", diarys);
            return "admindiary";
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    /**
     * 删除日记
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("deleteDiaryById")
    public String deleteDiaryById(HttpServletRequest request) {
        try {
            String id = request.getParameter("diaryid");
            int diaryid = Integer.parseInt(id);
            this.diaryService.deleteDiaryById(diaryid);
            return "redirect:admindiary";
        } catch (Exception e) {
            return null;
        }
    }

}
