package com.namerek.core.Helpers.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageProperties {
    private int length;
    private int width;
    private int height;

    @Override
    public String toString() {
        return length + "x" + width + "x" + height ;
    }
}