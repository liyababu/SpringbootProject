package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Menu;


public  interface MenuService
{
   List<Menu> getAllMenu();

  public  void saveNewMenuItem(Menu paramMenu);

  public  Menu getMenuById(long paramLong);

  public  void deleteItemById(long paramLong);

  public  Page<Menu> findPaginated(int paramInt1, int paramInt2, String paramString1, String paramString2);
}
