package io.greatgreven.rockpaperscissorapi.filters;

public class TieConditionFilter {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Boolean && !(Boolean) obj;
    }
}
