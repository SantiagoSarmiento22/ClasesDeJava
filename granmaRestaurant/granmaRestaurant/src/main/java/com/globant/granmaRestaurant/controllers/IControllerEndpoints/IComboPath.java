package com.globant.granmaRestaurant.controllers.IControllerEndpoints;

public interface IComboPath {
    String URL_BASE = "/combo";
    String CREATE_COMBO = "/create";
    String UUID = "/{uuid}";
    String UPDATE_CUSTOMER = "/update/{uuid}";
    String DELETE_CUSTOMER = "/delete/{uuid}";
}
