package com.wdd.test.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wdd.test.bean.PageParameter;



public class BaseController
{

	/**
	 * 默认ajax消息信息
	 */
	//protected final static MessageBody	DEFAULT_MESSAGE_BODY	= Global.getMessageBody(true);

	@InitBinder
	protected void ininBinder(WebDataBinder binder)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 获取Http请求
	 * 
	 * @return
	 */
	public HttpServletRequest getHttpServletRequest()
	{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest();
	}

	/**
	 * 获取分页信息
	 * 
	 * @return
	 */
	protected PageParameter getPageParameter()
	{
		HttpServletRequest request = this.getHttpServletRequest();
		PageParameter page = new PageParameter();
		//page.setPage(NumberUtils.toInt((request.getParameter("page")), 1));
		//page.setLimit(NumberUtils.toInt((request.getParameter("rows")), 20));
		//page.setSort(this.getSortParameter());
		return page;
	}

	/**
	 * 获取分页信息
	 * 
	 * @return
	 */
	protected PageParameter getPageParameter(Integer start, Integer limit)
	{
		PageParameter page = new PageParameter();
		page.setPage(start);
		page.setLimit(limit);
		return page;
	}

	/**
	 * 获取排序信息
	 * 
	 * @return
	 */
	/*protected SortParameter getSortParameter()
	{
		HttpServletRequest request = this.getHttpServletRequest();
		SortParameter sort = new SortParameter();
		sort.setDir(request.getParameter("sidx"));
		sort.setSort(request.getParameter("sord"));
		return sort;
	}
*/
	/**
	 * 获取查找实体
	 * 
	 * @return
	 */
	/*protected QueryEntity getQueryEntity()
	{
		return this.getQueryEntity(this.getHttpServletRequest().getParameter("query"));
	}
*/
	/**
	 * 获取查找实体
	 * 
	 * @return
	 */
	/*protected QueryEntity getQueryEntity(String query)
	{
		return this.getQueryEntity(query, null);
	}*/

	/**
	 * 获取查找实体
	 * 
	 * @return
	 */
	/*protected QueryEntity getQueryEntity(String query, Object bean)
	{
		return new QueryEntity(query, bean, UserUtils.getUser().getOperateHabit());
	}*/
}
