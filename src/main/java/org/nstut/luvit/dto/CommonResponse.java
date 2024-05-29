package org.nstut.luvit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {
    private Integer code;
    private String message;
    private String data;
}
