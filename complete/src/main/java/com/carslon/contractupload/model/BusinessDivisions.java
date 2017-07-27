package com.carslon.contractupload.model;

import com.carslon.contractupload.validation.BusinessDivisionValidator;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

@Component
public class BusinessDivisions {

    private static Logger LOG = LoggerFactory.getLogger(BusinessDivisions.class);
    private final TreeMap<String,BusinessDivision> businessDivisions;

    public BusinessDivisions(List<BusinessDivision> businessDivisionList) {
        this.businessDivisions = new TreeMap<>();
        for (BusinessDivision bd : businessDivisionList) {
            this.businessDivisions.put(bd.getName(),bd);
        }
    }

    //TODO::I added this no arg for testing purposes.
    public BusinessDivisions() {

        this.businessDivisions = new TreeMap<>();
        BusinessDivision bd = new BusinessDivision();
        bd.setId(1L);
        bd.setCode("Commercial");
        bd.setGroup("Meetings and Events");
        bd.setName("Commerciality");

        this.businessDivisions.put(bd.getCode(), bd);
    }

    public boolean exists(Set<String> codes){
        for (String code: codes) {
            if(businessDivisions.get(code) != null) {
                LOG.debug("BusinessDivision matched for incoming code:["+code+"]");
                continue;
            } else {
                LOG.debug("BusinessDivision !matched for incoming code:["+code+"]");
                return false;
            }
        }
        return true;
    }

    public boolean exists(String code){
        if (businessDivisions.get(code) != null) {
            LOG.debug("BusinessDivision matched for incoming code:[" + code + "]");
        } else {
            LOG.debug("BusinessDivision !matched for incoming code:[" + code + "]");
            return false;
        }
        return true;
    }

    public List<BusinessDivision> getAll(){
        return ImmutableList.copyOf(businessDivisions.values());
    }
}
