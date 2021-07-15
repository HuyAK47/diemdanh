package com.mta.diemdanh.repository;

import com.mta.diemdanh.entity.TblHocPhan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HocPhanRepository extends CrudRepository<TblHocPhan, Integer> {
}
