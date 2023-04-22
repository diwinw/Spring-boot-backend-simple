package com.miniBankend.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RequestSearchModelTest {

    @Test
    public void testModel() {
        // given
        String id = "123";
        String name = "Test Search";
        int minPrice = 100;
        int maxPrice = 200;
        String startDate = "2023-04-22";
        String endDate = "2023-04-30";

        // when
        RequestSearchModel model = new RequestSearchModel();
        model.setId(id);
        model.setName(name);
        model.setMin_price(minPrice);
        model.setMax_price(maxPrice);
        model.setStart_date(startDate);
        model.setEnd_date(endDate);

        // then
        assertThat(model.getId()).isEqualTo(id);
        assertThat(model.getName()).isEqualTo(name);
        assertThat(model.getMin_price()).isEqualTo(minPrice);
        assertThat(model.getMax_price()).isEqualTo(maxPrice);
        assertThat(model.getStart_date()).isEqualTo(startDate);
        assertThat(model.getEnd_date()).isEqualTo(endDate);
    }
}