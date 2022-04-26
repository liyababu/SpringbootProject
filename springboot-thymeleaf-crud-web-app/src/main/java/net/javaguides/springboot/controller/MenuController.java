package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Menu;
import net.javaguides.springboot.service.MenuService;

@Controller
public class MenuController
{

  @Autowired
  private MenuService menuService;

  @GetMapping({"/"})
  public String viewHomePage(Model model)
  {
    return findPaginated(1, "dishName", "asc", model);
  }

  @GetMapping({"/showNewItemForm"})
  public String showNewItemForm(Model model)
  {
    Menu menu = new Menu();
    model.addAttribute("menu", menu);
    return "new_menu";
  }

  @PostMapping({"/saveNewMenuItem"})
  public String saveNewMenuItem(@ModelAttribute("menu") Menu menu)
  {
    this.menuService.saveNewMenuItem(menu);
    return "redirect:/";
  }

  @GetMapping({"/showFormForUpdate/{id}"})
  public String showFormForUpdate(@PathVariable("id") long id, Model model)
  {
    Menu menu = this.menuService.getMenuById(id);

    model.addAttribute("menu", menu);
    return "update_menu";
  }

  @GetMapping({"/deleteMenu/{id}"})
  public String deleteMenu(@PathVariable("id") long id)
  {
    this.menuService.deleteItemById(id);
    return "redirect:/";
  }

  @GetMapping({"/page/{pageNo}"})
  public String findPaginated(@PathVariable("pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model)
  {
    int pageSize = 5;

    Page page = this.menuService.findPaginated(pageNo, pageSize, sortField, sortDir);
    List listMenuItem = page.getContent();

    model.addAttribute("currentPage", Integer.valueOf(pageNo));
    model.addAttribute("totalPages", Integer.valueOf(page.getTotalPages()));
    model.addAttribute("totalItems", Long.valueOf(page.getTotalElements()));

    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

    model.addAttribute("listMenuItem", listMenuItem);
    return "index";
  }
}