package com.cn.dao.util;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.flex.core.io.AmfCreator;
import org.springframework.flex.core.io.AmfProperty;

/**
 * 数据列表封装
 * 
 * @author linp
 * 
 * @param <T>
 */
@SuppressWarnings("serial")
public class PageForXmlString implements Serializable {

	/**
	 * 数据列表的xml字符串
	 */
	private String listXmlString;

	/**
	 * 每页数据量
	 */
	private int pageSize;

	/**
	 * 当前页
	 */
	private int pageCurrent;

	/**
	 * 数据总量
	 */
	private int total;

	/**
	 * 首页
	 * 
	 */
	private int pageFirst = 0;

	/**
	 * 末页
	 */
	private int pageLast;

	/**
	 * 总页数
	 */
	private int pageTotal;

	/**
	 * 数据页列表
	 */
	private ArrayList<Integer> pageList;

	/**
	 * 构造函数
	 * 
	 * @param listXmlString
	 * @param pageSize
	 * @param pageCurrent
	 * @param total
	 */
	@AmfCreator
	public PageForXmlString(@AmfProperty("listXmlString") String listXmlString,
			@AmfProperty("pageSize") int pageSize,
			@AmfProperty("pageCurrent") int pageCurrent,
			@AmfProperty("total") int total) {
		this.setListXmlString(listXmlString);
		this.setPageSize(pageSize);
		this.setPageCurrent(pageCurrent);
		this.setTotal(total);

		/**
		 * 确定首页
		 */
		this.pageFirst = 0;

		/**
		 * 确定末页
		 */
		if (total % pageSize == 0) {
			this.pageLast = total / pageSize - 1;
		} else {
			this.pageLast = total / pageSize;
		}

		/**
		 * 确定当前页
		 */
		if (pageCurrent < 0) {
			this.pageCurrent = 0;
		} else if (pageCurrent > this.pageLast) {
			this.pageCurrent = this.pageLast;
		} else {
			this.pageCurrent = pageCurrent;
		}

		/**
		 * 确定页面数列表，列出当前页附近的10个数据页
		 */
		int lastPageNumList = this.pageCurrent + 4;
		if (lastPageNumList > this.pageLast) {
			lastPageNumList = this.pageLast;
		}

		int firstPageNumList = lastPageNumList - 9;
		if (firstPageNumList < 0) {
			firstPageNumList = 0;
		}

		pageList = new ArrayList<Integer>();
		for (int i = firstPageNumList; i <= lastPageNumList; ++i) {
			pageList.add(i);
		}

		/**
		 * 确定总页数
		 */
		this.pageTotal = pageLast - pageFirst;
	}

	public String getListXmlString() {
		return listXmlString;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	public int getPageFirst() {
		return pageFirst;
	}

	public int getPageLast() {
		return pageLast;
	}

	public ArrayList<Integer> getPageList() {
		return pageList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public int getTotal() {
		return total;
	}

	public void setListXmlString(String listXmlString) {
		this.listXmlString = listXmlString;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public void setPageFirst(int pageFirst) {
		this.pageFirst = pageFirst;
	}

	public void setPageLast(int pageLast) {
		this.pageLast = pageLast;
	}

	public void setPageList(ArrayList<Integer> pageList) {
		this.pageList = pageList;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
