package com.wqb.controller;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.wqb.entity.Message;
import com.wqb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.common.Result;
import com.wqb.common.QueryPageParam;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    public List<Message> list() {
        return messageService.list();
    }

    @PostMapping("/save")
    public Result save(@RequestBody Message message) {
        Timestamp createTime = new Timestamp(new Date().getTime());

        message.setCreateTime(createTime);

        return messageService.save(message) ? Result.suc() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Message message) {
        return messageService.updateById(message) ? Result.suc() : Result.fail();
    }

//    @GetMapping("/del")
//    public Result del(@RequestParam String id) {
//        return messageService.removeById(id) ? Result.suc() : Result.fail();
//    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody Message message) {
        return messageService.updateById(message);
    }

    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list = messageService.lambdaQuery().eq(Message::getMessageId,no).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Message message) {
        return messageService.saveOrUpdate(message);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return messageService.removeById(id);
    }

//    @PostMapping("/listP")
//    public Result listP(@RequestBody Message message) {
//        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if(StringUtils.isNotBlank(message.getCName())) {
//            lambdaQueryWrapper.like(Message::getCName, message.getCName());// 模糊查询
//        }
//        return Result.suc(messageService.list(lambdaQueryWrapper));
//    }

    /**
     * 查询全部信息,默认最新在前
     * @param query
     * @return
     */
    @PostMapping("/selectAll")
    public Result selectAll(@RequestBody QueryPageParam query) {

        Page<Message> messagePage = new Page<>();
        messagePage.setCurrent(query.getPageNum());
        messagePage.setSize(query.getPageSize());

        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Message::getCreateTime);

        IPage result = messageService.page(messagePage, lambdaQueryWrapper);

        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }

    /**
     * 根据信息内容模糊查询
     * @param query
     * @return
     */
    @PostMapping("/selectByContext")
    public Result selectByContext(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String context = (String)param.get("context");
        Page<Message> messagePage = new Page<>();
        messagePage.setCurrent(query.getPageNum());
        messagePage.setSize(query.getPageSize());

        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Message::getContext, context).orderByDesc(Message::getCreateTime);

        IPage result = messageService.page(messagePage, lambdaQueryWrapper);

        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }

    /**
     * 查询最近一条记录
     * @return
     */
    @GetMapping("/selectRecent")
    public Result selectRecent() {

        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Message::getCreateTime).last("limit 1");

        Message msg = messageService.getOne(lambdaQueryWrapper);


        return Result.suc(msg);
    }
}

