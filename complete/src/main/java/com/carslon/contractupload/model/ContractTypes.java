package com.carslon.contractupload.model;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.TreeMap;

public class ContractTypes {

    private final TreeMap<String,ContractType> contractTypes;

    public ContractTypes(List<ContractType> contractTypes) {
        this.contractTypes = new TreeMap<>();
        for (ContractType ct : contractTypes) {
            this.contractTypes.put(ct.getName(),ct);
        }
    }

    public boolean exists(String code){
        return contractTypes.get(code) != null;
    }

    public List<ContractType> getAll(){
        return ImmutableList.copyOf(contractTypes.values());
    }

}
