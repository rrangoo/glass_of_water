package ru.hse.glassofwater.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Status {
    String status;

    public Status(String status) {
        this.status = status;
    }
}
