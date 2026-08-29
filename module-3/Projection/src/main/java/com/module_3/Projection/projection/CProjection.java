package com.module_3.Projection.projection;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CProjection {
    final Long id;
    final String name;
    final String email;
}
