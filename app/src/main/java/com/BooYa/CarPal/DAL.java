package com.BooYa.CarPal;

/**
 * Created by adam on 23/11/2014.
 */
public class DAL {
    public static GroupInfo sta_groupInfo = null;


    public static void fillFakeData()
    {
        if(sta_groupInfo == null) {
            sta_groupInfo = new GroupInfo();
            sta_groupInfo.set_groupName("MAROON 5");

            DAL.sta_groupInfo.get_groupMembers()
                            .add(new UserInfo("972542501908")
                            .set_userName("ME")
                            .set_userLastName("")
                            .set_addressHome(new Address()
                                            .set_countryAddress("ISRAEL")
                                            .set_cityAddress("TEL AVIV")
                                            .set_streetNameAddress("HAPODIM")
                                            .set_streetNumberAddress(10)
                            )
                            .set_organizationName("APPLE")
                            .set_addressWork(new Address().set_countryAddress("ISRAEL")
                            .set_cityAddress("HERTZHELIA")
                            .set_streetNameAddress("MASKIT")
                            .set_streetNumberAddress(15))
            );


            DAL.sta_groupInfo.get_groupMembers()
                    .add(new UserInfo("972522910780")
                                    .set_userName("RON")
                                    .set_userLastName("AHARON")
                                    .set_addressHome(new Address()
                                                    .set_countryAddress("ISRAEL")
                                                    .set_cityAddress("TEL AVIV")
                                                    .set_streetNameAddress("HAPODIM")
                                                    .set_streetNumberAddress(1)
                                    )
                                    .set_organizationName("APPLE")
                                    .set_addressWork(new Address().set_countryAddress("ISRAEL")
                                            .set_cityAddress("HERTZHELIA")
                                            .set_streetNameAddress("MASKIT")
                                            .set_streetNumberAddress(15))
                    );

            DAL.sta_groupInfo.get_groupMembers()
                    .add(new UserInfo("972548018050")
                                    .set_userName("TAL")
                                    .set_userLastName("LEVI")
                                    .set_addressHome(new Address()
                                                    .set_countryAddress("ISRAEL")
                                                    .set_cityAddress("TEL AVIV")
                                                    .set_streetNameAddress("BAR KOCHVA")
                                                    .set_streetNumberAddress(32)
                                    )
                                    .set_organizationName("APPLE")
                                    .set_addressWork(new Address().set_countryAddress("ISRAEL")
                                            .set_cityAddress("HERTZHELIA")
                                            .set_streetNameAddress("MASKIT")
                                            .set_streetNumberAddress(15))
                    );

            DAL.sta_groupInfo.get_groupMembers()
                    .add(new UserInfo("972585345345")
                                    .set_userName("AVI")
                                    .set_userLastName("COHEN")
                                    .set_addressHome(new Address()
                                                    .set_countryAddress("ISRAEL")
                                                    .set_cityAddress("TEL AVIV")
                                                    .set_streetNameAddress("HA'AMAL")
                                                    .set_streetNumberAddress(42)
                                    )
                                    .set_organizationName("APPLE")
                                    .set_addressWork(new Address().set_countryAddress("ISRAEL")
                                            .set_cityAddress("HERTZHELIA")
                                            .set_streetNameAddress("MASKIT")
                                            .set_streetNumberAddress(15))
                    );

            DAL.sta_groupInfo.get_groupMembers()
                    .add(new UserInfo("972542501908")
                                    .set_userName("LINA")
                                    .set_userLastName("COHEN")
                                    .set_addressHome(new Address()
                                                    .set_countryAddress("ISRAEL")
                                                    .set_cityAddress("TEL AVIV")
                                                    .set_streetNameAddress("NAHALAT GANIM")
                                                    .set_streetNumberAddress(1)
                                    )
                                    .set_organizationName("APPLE")
                                    .set_addressWork(new Address().set_countryAddress("ISRAEL")
                                            .set_cityAddress("HERTZHELIA")
                                            .set_streetNameAddress("MASKIT")
                                            .set_streetNumberAddress(15))
                    );
        }




    }

}
