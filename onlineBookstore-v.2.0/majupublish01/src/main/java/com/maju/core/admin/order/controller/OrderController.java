package com.maju.core.admin.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maju.common.pagination.PageBean;
import com.maju.common.pagination.PageConstants;
import com.maju.common.web.ConstantsCommon;
import com.maju.core.admin.order.bean.Order;
import com.maju.core.admin.order.service.OrderService;
@Controller
@RequestMapping(value = "/admin/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	/**
	 * 获取所有订单
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/getAllOrder.do")
	public String getAllOrder(Order order, Integer pc, ModelMap model, HttpServletRequest request){
		int pageCode = PageBean.pc(pc);
		int pageSize = PageConstants.ORDER_PAGE_SIZE;
		int startRow = PageBean.startRow(pageCode,pageSize);
		StringBuilder params = new StringBuilder();
		if(null!=order){
			if(null!=order.getStatus()){
				params.append("status="+order.getStatus()+"&");
			}
		}
		PageBean<Order> pb = orderService.getAllOrder(order,startRow,pageSize);
		pb.setPc(pageCode);
		String url =ConstantsCommon.PROJECT_NAME_URL+"/admin/order/getAllOrder.do";
		pb.setUrl(pb.url(url, params.toString()));
		model.addAttribute("pb", pb);
		return "order/list";
	}
	
	/**
	 * 根据ID订单获取订单
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/getOrderById.do")
	public String getOrderById(String oid, String btn, ModelMap model){
		if(StringUtils.isNotBlank(oid)){
			Order order = orderService.getOrderById(oid);
			model.addAttribute("order", order);
			model.addAttribute("btn", btn);//btn说明了用户点击哪个超链接来访问本方法的
			return "order/view";
		}
		return null;
	}
	
	/**
	 * 根据ID取消订单
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/cancel.do")
	public String cancel(String oid, ModelMap model){
		/*
		 * 校验订单状态
		 */
		Integer status = orderService.findStatus(oid);
		if(status != 1) {
			model.addAttribute("code", "error");
			model.addAttribute("msg", "状态不对，不能取消！");
			model.addAttribute("backLick", "/admin/order/getAllOrder.do?status="+status);
			return "msg";
		}
		Integer i = orderService.updateStatus(oid, 5);//设置状态为取消！
		if(i>0){
			model.addAttribute("code", "success");
			model.addAttribute("msg", "您的订单已取消，您不后悔吗！");
		}else{
			model.addAttribute("code", "error");
			model.addAttribute("msg", "订单取消失败！");
		}
		model.addAttribute("backLick", "/admin/order/getAllOrder.do?status="+status);
		return "msg";	
	}
	
	/**
	 * 发货
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/deliver.do")
	public String deliver(String oid, ModelMap model){
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if(status != 2) {
			model.addAttribute("code", "error");
			model.addAttribute("msg", "状态不对，不能发货！");
			model.addAttribute("backLick", "/admin/order/getAllOrder.do?status="+status);
			return "msg";
		}
		Integer i = orderService.updateStatus(oid, 3);//设置状态为取消！
		if(i>0){
			model.addAttribute("code", "success");
			model.addAttribute("msg", "发货成功！");
		}else{
			model.addAttribute("code", "error");
			model.addAttribute("msg", "发货失败！");
		}
		model.addAttribute("backLick", "/admin/order/getAllOrder.do?status="+status);
		return "msg";		
	}
	
}
