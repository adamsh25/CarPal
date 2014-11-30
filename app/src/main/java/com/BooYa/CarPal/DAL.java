package com.BooYa.CarPal;

import android.content.Context;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



import java.util.List;

/**
 * Created by adam on 23/11/2014.
 */
public class DAL {
    private static GroupInfo sta_groupInfo = null;
    private static ArrayList<TLVLocation> sta_TLVLocations = null;


    private static void fillFakeData()
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
                                            .set_streetNameAddress("DIZENGOFF")
                                            .set_streetNumberAddress("10")
                            )
                            .set_organizationName("APPLE")
                            .set_addressWork(new Address().set_countryAddress("ISRAEL")
                            .set_cityAddress("HERTZHELIA")
                            .set_streetNameAddress("MASKIT")
                            .set_streetNumberAddress("15"))
                            .set_imgRecourceID(R.drawable.face1)
            );


            DAL.sta_groupInfo.get_groupMembers()
                    .add(new UserInfo("972522910780")
                                    .set_userName("RON")
                                    .set_userLastName("AHARON")
                                    .set_addressHome(new Address()
                                                    .set_countryAddress("ISRAEL")
                                                    .set_cityAddress("TEL AVIV")
                                                    .set_streetNameAddress("BOGRASHOV")
                                                    .set_streetNumberAddress("1")
                                    )
                                    .set_organizationName("APPLE")
                                    .set_addressWork(new Address().set_countryAddress("ISRAEL")
                                            .set_cityAddress("HERTZHELIA")
                                            .set_streetNameAddress("MASKIT")
                                            .set_streetNumberAddress("15"))
                                    .set_imgRecourceID(R.drawable.face2)
                    );

            DAL.sta_groupInfo.get_groupMembers()
                    .add(new UserInfo("972548018050")
                                    .set_userName("TAL")
                                    .set_userLastName("LEVI")
                                    .set_addressHome(new Address()
                                                    .set_countryAddress("ISRAEL")
                                                    .set_cityAddress("TEL AVIV")
                                                    .set_streetNameAddress("ALLENBY")
                                                    .set_streetNumberAddress("32")
                                    )
                                    .set_organizationName("APPLE")
                                    .set_addressWork(new Address().set_countryAddress("ISRAEL")
                                            .set_cityAddress("HERTZHELIA")
                                            .set_streetNameAddress("MASKIT")
                                            .set_streetNumberAddress("15"))
                                    .set_imgRecourceID(R.drawable.face3)
                    );

            DAL.sta_groupInfo.get_groupMembers()
                    .add(new UserInfo("972585345345")
                                    .set_userName("AVI")
                                    .set_userLastName("COHEN")
                                    .set_addressHome(new Address()
                                                    .set_countryAddress("ISRAEL")
                                                    .set_cityAddress("TEL AVIV")
                                                    .set_streetNameAddress("BEN YEHUDA")
                                                    .set_streetNumberAddress("42")
                                    )
                                    .set_organizationName("APPLE")
                                    .set_addressWork(new Address().set_countryAddress("ISRAEL")
                                            .set_cityAddress("HERTZHELIA")
                                            .set_streetNameAddress("MASKIT")
                                            .set_streetNumberAddress("15"))
                                    .set_imgRecourceID(R.drawable.face4)
                    );

            DAL.sta_groupInfo.get_groupMembers()
                    .add(new UserInfo("972542501908")
                                    .set_userName("LINA")
                                    .set_userLastName("COHEN")
                                    .set_addressHome(new Address()
                                                    .set_countryAddress("ISRAEL")
                                                    .set_cityAddress("TEL AVIV")
                                                    .set_streetNameAddress("GORDON")
                                                    .set_streetNumberAddress("1")
                                    )
                                    .set_organizationName("APPLE")
                                    .set_addressWork(new Address().set_countryAddress("ISRAEL")
                                            .set_cityAddress("HERTZHELIA")
                                            .set_streetNameAddress("MASKIT")
                                            .set_streetNumberAddress("15"))
                                    .set_imgRecourceID(R.drawable.face5)
                    );
        }




    }

    public static GroupInfo getSta_groupInfo() {
        if(sta_groupInfo == null)
        {
            fillFakeData();
        }
        return  sta_groupInfo;
    }

    public static ArrayList<TLVLocation> getSta_TLVLocations() {
        if(sta_TLVLocations == null)
        {
            sta_TLVLocations = new ArrayList<TLVLocation>();
            fillTLVLocations();
        }
        return  sta_TLVLocations;
    }

    public static void setSta_groupInfo(GroupInfo sta_groupInfo) {
        DAL.sta_groupInfo = sta_groupInfo;
    }


    private static void fillTLVLocations()
    {
        String URL = "http://gisn.tel-aviv.gov.il/wsgis/service.asmx?wsdl";
       // try {
           // Call  call   = new Call(URL);
       // } catch (MalformedURLException e) {
         //   e.printStackTrace();
      //  }
        String NAME_SPACE= "http://tempuri.org/";
        String OPERATION_NAME = "GetLayer";
        String ACTION = "http://tempuri.org/GetLayer";
        //SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
        InputStream file = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

        try {
            JsonFactory f = new MappingJsonFactory();
            JsonParser jp = f.createParser(file);

            JsonToken current;
            current = jp.nextToken();

            if (current != JsonToken.START_ARRAY) {
                System.out.println("Error: root should be array: quiting.");
                return;
            }

            while (jp.nextToken() != JsonToken.END_ARRAY) {
                current = jp.nextToken();

                if (current != JsonToken.FIELD_NAME) {
                    return;
                }

                TLVLocation curr = new TLVLocation();

                while (current != JsonToken.END_OBJECT) {

                    String fieldName = jp.getCurrentName();
                    // move from field name to field value
                    current = jp.nextToken();

                    if (fieldName == "house_num") {
                        curr.set_address(jp.readValueAs(Integer.class).toString());
                    } else if (fieldName == "street_code") {
                        curr.set_locationName(jp.readValueAs(Integer.class).toString());
                    } else if (fieldName == "street_name") {
                        curr.set_location(BL.GetLatLngFromAddress(BL.CONTEXT, jp.readValueAs(String.class)));
                    } else {
                        curr.set_description(jp.readValueAs(String.class));
                    }

                    current = jp.nextToken();
                }

                sta_TLVLocations.add(curr);
            }

        } catch (IOException e) {
        } finally {

        }
    }

    public static void fillFakeTLVLocations()
    {
        sta_TLVLocations.add(new TLVLocation().set_address(""));

    }



}
