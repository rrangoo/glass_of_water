package ru.hse.glassofwater.mapper;

public interface Mapper<Dto, Model> {
    Dto to(Model model);

    Model from(Dto dto);
}
