package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu
{

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;

  @Column(name="dish_name")
  private String dishName;

  @Column(name="description")
  private String description;

  @Column(name="category")
  private String category;

  public long getId()
  {
    return this.id;
  }
  public String getDishName() {
    return this.dishName;
  }
  public void setDishName(String dishName) {
    this.dishName = dishName;
  }
  public String getDescription() {
    return this.description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getCategory() {
    return this.category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public void setId(long id) {
    this.id = id;
  }
}