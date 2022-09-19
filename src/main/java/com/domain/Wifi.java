package com.domain;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor(staticName = "of")
@ToString
public class Wifi implements Comparable<Wifi>{
    @SerializedName("X_SWIFI_MGR_NO")
    private String mgrNo;        // 관리번호

    @SerializedName("X_SWIFI_WRDOFC")
    private String gu;           // 자치구

    @SerializedName("X_SWIFI_MAIN_NM")
    private String wifiName; // 와이파이명

    @SerializedName("X_SWIFI_ADRES1")
    private String adres1;       // 도로명주소

    @SerializedName("X_SWIFI_ADRES2")
    private String adres2;       // 상세주소

    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String instlFloor;  // 설치위치(층)

    @SerializedName("X_SWIFI_INSTL_TY")
    private String instlTY;      // 설치 유형

    @SerializedName("X_SWIFI_INSTL_MBY")
    private String instlMBY;     // 설치기관

    @SerializedName("X_SWIFI_SVC_SE")
    private String svc;          // 서비스 구분

    @SerializedName("X_SWIFI_CMCWR")
    private String networkType;   // 망종류

    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private String instlYear;     // 설치 년도

    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String inOutDoor;    // 실내외 구분

    @SerializedName("X_SWIFI_REMARS3")
    private String connEnvironment; // WIFI 접속 환경

    @SerializedName("LNT")
    private Double lat;             // x좌표(위도)

    @SerializedName("LAT")
    private Double lnt;             // y좌표(경도)

    @SerializedName("WORK_DTTM")
    private String workDateTime;    // 작업일자


    // reverseOrder
    @Override
    public int compareTo(@NotNull Wifi o) {
        return o.mgrNo.compareTo(mgrNo);
    }
}
