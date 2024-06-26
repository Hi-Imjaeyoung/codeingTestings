package com.example.demo.Item.dto;

import com.example.demo.Item.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class SelectItem {
    private Long id;
    private String name;
    private int price;

    public SelectItem(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
    }
    @Override
    public int hashCode(){
        return Objects.hash(id,name,price);
    }

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        SelectItem that = (SelectItem) object;
        return Objects.equals(name, that.name) && price == that.price && Objects.equals(id, that.id);
    }
    public ItemResponseDto toDto(){
        return new ItemResponseDto(null,this.name,this.price);
    }
}
