package com.decodedbytes.beans;

import lombok.Data;

@Data
public class OutboundNameAddress {

    private String name;

    private String address;

    public OutboundNameAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }


}
