package com.module_3.Projection;

import com.module_3.Projection.projection.CNewProjection;
import com.module_3.Projection.projection.CProjection;
import com.module_3.Projection.projection.IProjection;
import com.module_3.Projection.repositories.PatientRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTest {
    @Autowired
    PatientRepositories patientRepositories;

    @Test
    void test()
    {
        List<IProjection>  iProjections = patientRepositories.getIProjectionInfo();
        for(IProjection p : iProjections)
        {
            System.out.println(p.getId()+" "+p.getName()+" "+p.getEmail());
        }
    }

    @Test
    void test1()
    {
        List<CProjection> cProjections = patientRepositories.getCProjection();
        for(CProjection c : cProjections)
        {
            System.out.println(c);
        }
    }

    @Test
    void test2()
    {
        List<CNewProjection> cNewProjections = patientRepositories.getCNewProjection();
        for (CNewProjection cNewProjection : cNewProjections)
        {
            System.out.println(cNewProjection);
        }
    }

    @Test
    void test4()
    {
        int count = patientRepositories.updateRow(1L,"Anuj");
        System.out.println(count);
    }
}
