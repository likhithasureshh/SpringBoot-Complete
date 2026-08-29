package com.module_3.Projection.projection;

import com.module_3.Projection.enums.BloodGroup;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data

public class CNewProjection {
     final BloodGroup bloodGroup;
     final Long count;


}
