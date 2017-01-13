package com.yhq.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.me.controller.BaseController;
import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.yhq.model.BookShelf;
import com.yhq.model.BookShelfCollection;
import com.yhq.model.Comment;
import com.yhq.model.User;
import com.yhq.service.ShelfService;

@RestController
@RequestMapping("/bookshelves")
public class ShelfController extends BaseController{
	
	@Autowired ShelfService shelfService;
	
	/**
	 * 获得当前用户的某个书架
	 * @param id
	 * @param authorization
	 * @return
	 */
	public String getUserOneBookShelves(
			@PathVariable Long id,
			@RequestHeader String authorization
			) {
		User user = HttpKit.getCurrentUser(authorization);
		Message message = shelfService.getUserOneShelf(user, id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	/**
	 * 获得当前用户的书架
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value = "/bookshelves", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getUserBookShelves(
			@RequestHeader String authorization
			){
		User user = HttpKit.getCurrentUser(authorization);
		Message message = shelfService.getUserShelves(user);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	/**
	 * 获得某个书架的评论
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/comments",method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getShelfComments(@PathVariable Long id){
		Message message= shelfService.getShelfComments(id);
		Response response= new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 对某个书架评价
	 * @param id
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="/{id}/comments",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String commentShelf(@PathVariable Long id, @RequestBody Comment comment){
		Message message=shelfService.commentShelf(id, comment);
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 获得当前用户的收藏的书架
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value="/collection",method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getCurrentUserShelves(@RequestHeader String authorization){
		User user=HttpKit.getCurrentUser(authorization);
		Message message=shelfService.getCurrentUserShelves(user.getId());
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 收藏某个书架
	 * @param id
	 * @param bookShelfCollection
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value="/{id}/collection",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveShelf(@PathVariable Long id,@RequestBody BookShelfCollection bookShelfCollection,@RequestHeader String authorization){
		User user=HttpKit.getCurrentUser(authorization);
		Message message=shelfService.saveShelf(id, bookShelfCollection, user.getId());
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 更新当前用户某个书架
	 * @param id
	 * @param bookShelf
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT,produces = "application/json;charset=UTF-8")
	public String updateCurrentUserShelf(@PathVariable Long id,@RequestBody BookShelf bookShelf,@RequestHeader String authorization){
		Message message=shelfService.updateCurrentUserShelf(id, bookShelf,
//				Long.valueOf((long) 1)
				HttpKit.getCurrentUser(authorization).getId()
				);
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 创建一个属于当前用户的书架
	 * @param bookShelf
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String createBookShelf(@RequestBody BookShelf bookShelf,@RequestHeader String authorization) {
		Message message=shelfService.createBookShelf(bookShelf,
//				Long.valueOf((long)1
				HttpKit.getCurrentUser(authorization).getId()
				);
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 设置喜欢某个书架
	 * @param id
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value="/{id}/like", method=RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public String likeBookShelf(
			@PathVariable Long id,
			@RequestHeader String authorization
			){
		Message message = shelfService.likeBookshelf(authorization, id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	/**
	 * 设置不喜欢某个书架
	 * @param id
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value="/{id}/unlike",method=RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public String dislikeBookShelf(@PathVariable Long id,@RequestHeader String authorization){
		User user=HttpKit.getCurrentUser(authorization);
		Message message=shelfService.dislikeBookShelf(id,user.getId());
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
}
