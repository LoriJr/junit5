package com.viratech.service.external;

import com.viratech.domain.Conta;
import com.viratech.service.enums.EventType;

public interface ContaEvent {

    void dispatch(Conta conta, EventType type);
}
