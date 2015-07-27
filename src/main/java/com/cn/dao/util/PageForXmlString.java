package com.cn.dao.util;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.flex.core.io.AmfCreator;
import org.springframework.flex.core.io.AmfProperty;

/**
 * �����б��װ
 * 
 * @author linp
 * 
 * @param <T>
 */
@SuppressWarnings("serial")
public class PageForXmlString implements Serializable {

	/**
	 * �����б��xml�ַ���
	 */
	private String listXmlString;

	/**
	 * ÿҳ������
	 */
	private int pageSize;

	/**
	 * ��ǰҳ
	 */
	private int pageCurrent;

	/**
	 * ��������
	 */
	private int total;

	/**
	 * ��ҳ
	 * 
	 */
	private int pageFirst = 0;

	/**
	 * ĩҳ
	 */
	private int pageLast;

	/**
	 * ��ҳ��
	 */
	private int pageTotal;

	/**
	 * ����ҳ�б�
	 */
	private ArrayList<Integer> pageList;

	/**
	 * ���캯��
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
		 * ȷ����ҳ
		 */
		this.pageFirst = 0;

		/**
		 * ȷ��ĩҳ
		 */
		if (total % pageSize == 0) {
			this.pageLast = total / pageSize - 1;
		} else {
			this.pageLast = total / pageSize;
		}

		/**
		 * ȷ����ǰҳ
		 */
		if (pageCurrent < 0) {
			this.pageCurrent = 0;
		} else if (pageCurrent > this.pageLast) {
			this.pageCurrent = this.pageLast;
		} else {
			this.pageCurrent = pageCurrent;
		}

		/**
		 * ȷ��ҳ�����б��г���ǰҳ������10������ҳ
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
		 * ȷ����ҳ��
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
