package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Menu;
import net.javaguides.springboot.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService
{

  @Autowired
  private MenuRepository menuRepository;

  public List<Menu> getAllMenu()
  {
    return this.menuRepository.findAll();
  }

  public void saveNewMenuItem(Menu menu)
  {
    this.menuRepository.save(menu);
  }

  public Menu getMenuById(long id)
  {
    Optional optional = this.menuRepository.findById(Long.valueOf(id));
    Menu menu = null;
    if (optional.isPresent())
      menu = (Menu)optional.get();
    else {
      throw new RuntimeException(" Item not found for id :: " + id);
    }
    return menu;
  }

  public void deleteItemById(long id)
  {
    this.menuRepository.deleteById(Long.valueOf(id));
  }

  public Page<Menu> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection)
  {
    Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(new String[] { sortField }).ascending() : 
      Sort.by(new String[] { sortField }).descending();

    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    return this.menuRepository.findAll(pageable);
  }
}