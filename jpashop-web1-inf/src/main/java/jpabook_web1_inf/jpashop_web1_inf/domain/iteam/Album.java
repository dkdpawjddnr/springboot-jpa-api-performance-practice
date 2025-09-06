package jpabook_web1_inf.jpashop_web1_inf.domain.iteam;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;
    private String etc;
}
