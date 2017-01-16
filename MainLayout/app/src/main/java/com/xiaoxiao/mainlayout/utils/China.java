package com.xiaoxiao.mainlayout.utils;

import java.util.ArrayList;

/**
 * Created by xuwenting 2016/4/28.
 */
public class China {
    public ArrayList<Province> citylist;

    public class Province {
        public ArrayList<Area> c	;
        public String p;

        public  class Area{
            public ArrayList<Street> a;
            public String n;
            public class Street{
                public String s;
            }
        }
    }
}
