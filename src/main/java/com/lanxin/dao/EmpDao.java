package com.lanxin.dao;

import java.util.Set;

public interface EmpDao {

    public String selectEmpName(String ename);

    public Set<String> selectRname(String ename);

    public Set<String> selectQname(String ename);
}
