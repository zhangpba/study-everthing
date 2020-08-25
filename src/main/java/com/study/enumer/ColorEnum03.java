package com.study.enumer;

/**
 * 枚举中实现接口
 */
public enum ColorEnum03 implements ColorInterface {
    RED {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String getDescription() {
            return "我是红色";
        }
    },
    BLUE {
        @Override
        public int getValue() {
            return 2;
        }

        @Override
        public String getDescription() {
            return "我是蓝色";
        }
    },
    GREEN {
        @Override
        public int getValue() {
            return 3;
        }

        @Override
        public String getDescription() {
            return "我是绿色";
        }
    }
}
