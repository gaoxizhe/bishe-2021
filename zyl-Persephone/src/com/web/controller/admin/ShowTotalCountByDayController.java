package com.web.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.biz.OrderItemBiz;
import com.web.biz.impl.OrderItemBizImpl;

@WebServlet(urlPatterns="/showTotalCountByDay")
public class ShowTotalCountByDayController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 调用业务逻辑层的方法
		OrderItemBiz orderItemBiz = new OrderItemBizImpl();
		List<HashMap<String, String>> list = orderItemBiz.showTotalCountByDay();
		
		// 分离各数据
		List<Integer> coffee = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
		List<Integer> tea = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
		List<Integer> milkTea = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
		List<Integer> fruitTea = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
		List<Integer> milk = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
		List<String> date = new ArrayList<String>();
		String d = list.get(0).get("date");
		date.add(d);
		int len = 0;
		for (HashMap<String, String> map : list) {
			if (!map.get("date").equals(d)) {
				len++;
				d = map.get("date");
				date.add(d);
			}
			if (map.get("type").equals("Coffee"))
				coffee.set(len, Integer.valueOf(map.get("num")).intValue());
			else if (map.get("type").equals("Tea"))
				tea.set(len, Integer.valueOf(map.get("num")).intValue());
			else if (map.get("type").equals("MilkTea"))
				milkTea.set(len, Integer.valueOf(map.get("num")).intValue());
			else if (map.get("type").equals("FruitTea"))
				fruitTea.set(len, Integer.valueOf(map.get("num")).intValue());
			else if (map.get("type").equals("Milk"))
				milk.set(len, Integer.valueOf(map.get("num")).intValue());
		}
		
		// 返回json数据
		Gson gson = new Gson();
		String json = "{\"date\": " + gson.toJson(date) + ", \"coffee\": " + gson.toJson(coffee) + ", \"tea\": " + gson.toJson(tea)
			+ ", \"milkTea\": " + gson.toJson(milkTea) + ", \"fruitTea\": " + gson.toJson(fruitTea) + ", \"milk\": " + gson.toJson(milk) + "}";
		resp.getWriter().append(json);
	}
}
