package com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu;

public class Filter {
    private final String filter1;
    private final String filter2;
    private final String filter3;
    private final String filter4;
    private final String filter5;
    private final String filter6;
    private final String filter7;
    private final String filter8;
    private final String filter9;
    private final String filter10;
    private static final String[] FILTER_ARRAY = new String[9];

    public Filter(Builder builder) {
        this.filter1 = builder.filter1;
        this.filter2 = builder.filter2;
        this.filter3 = builder.filter3;
        this.filter4 = builder.filter4;
        this.filter5 = builder.filter5;
        this.filter6 = builder.filter6;
        this.filter7 = builder.filter7;
        this.filter8 = builder.filter8;
        this.filter9 = builder.filter9;
        this.filter10 = builder.filter10;
    }

    public String[] getFilterArray(){
        return FILTER_ARRAY;
    }

    public static class Builder {
        private String filter1;
        private String filter2;
        private String filter3;
        private String filter4;
        private String filter5;
        private String filter6;
        private String filter7;
        private String filter8;
        private String filter9;
        private String filter10;
        private static int counter;

        public static Builder newInstance() {
            counter = 0;
            return new Builder();
        }

        private Builder() {
        }

        public Builder setFilter1(String filter1) {
            this.filter1 = filter1;
            FILTER_ARRAY[counter] = filter1;
            counter++;
            return this;
        }

        public Builder setFilter2(String filter2) {
            this.filter2 = filter2;
            FILTER_ARRAY[counter] = filter2;
            counter++;
            return this;
        }

        public Builder setFilter3(String filter3) {
            this.filter3 = filter3;
            FILTER_ARRAY[counter] = filter3;
            counter++;
            return this;
        }

        public Builder setFilter4(String filter4) {
            this.filter4 = filter4;
            FILTER_ARRAY[counter] = filter4;
            counter++;
            return this;
        }

        public Builder setFilter5(String filter5) {
            this.filter5 = filter5;
            FILTER_ARRAY[counter] = filter5;
            counter++;
            return this;
        }

        public Builder setFilter6(String filter6) {
            this.filter6 = filter6;
            FILTER_ARRAY[counter] = filter6;
            counter++;
            return this;
        }

        public Builder setFilter7(String filter7) {
            this.filter7 = filter7;
            FILTER_ARRAY[counter] = filter7;
            counter++;
            return this;
        }

        public Builder setFilter8(String filter8) {
            this.filter8 = filter8;
            FILTER_ARRAY[counter] = filter8;
            counter++;
            return this;
        }

        public Builder setFilter9(String filter9) {
            this.filter9 = filter9;
            FILTER_ARRAY[counter] = filter9;
            counter++;
            return this;
        }

        public Builder setFilter10(String filter10) {
            this.filter10 = filter10;
            FILTER_ARRAY[counter] = filter10;
            counter++;
            return this;
        }

        public Filter build(){
            return new Filter(this);
        }
    }
}
