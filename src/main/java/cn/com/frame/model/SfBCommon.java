package cn.com.frame.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Table(name = "sf_b_common")
public class SfBCommon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigInteger wf_id;
    private BigInteger trace_id;
    private BigInteger dynamic_instance_id;
    private String uuid;
    private String a01="";
    private String a02="";
    private String a03="";
    private String a04="";
    private String a05="";
    private String a06="";
    private String a07="";
    private String a08="";
    private String a09="";
    private String a10="";
    private String a11="";
    private String a12="";
    private String a13="";
    private String a14="";
    private String a15="";
    private String a16="";
    private String a17="";
    private String a18="";
    private String a19="";
    private String a20="";
    private String a21="";
    private String a22="";
    private String a23="";
    private String a24="";
    private String a25="";
    private String a26="";
    private String a27="";
    private String a28="";
    private String a29="";
    private String a30="";
    private String a31="";
    private String a32="";
    private String a33="";
    private String a34="";
    private String a35="";
    private String a36="";
    private String a37="";
    private String a38="";
    private String a39="";
    private String a40="";
    private String a41="";
    private String a42="";
    private String a43="";
    private String a44="";
    private String a45="";
    private String a46="";
    private String a47="";
    private String a48="";
    private String a49="";
    private String a50="";
    private String a51="";
    private String a52="";
    private String a53="";
    private String a54="";
    private String a55="";
    private String a56="";
    private String a57="";
    private String a58="";
    private String a59="";
    private String a60="";
    private String a61="";
    private String a62="";
    private String a63="";
    private String a64="";
    private String a65="";
    private String a66="";
    private String a67="";
    private String a68="";
    private String a69="";
    private String a70="";
    private String a71="";
    private String a72="";
    private String a73="";
    private String a74="";
    private String a75="";
    private String a76="";
    private String a77="";
    private String a78="";
    private String a79="";
    private String a80="";
    private String a81="";
    private String a82="";
    private String a83="";
    private String a84="";
    private String a85="";
    private String a86="";
    private String a87="";
    private String a88="";
    private String a89="";
    private String a90="";
    private String a91="";
    private String a92="";
    private String a93="";
    private String a94="";
    private String a95="";
    private String a96="";
    private String a97="";
    private String a98="";
    private String a99="";
    private String a100="";
    private String a101="";
    private String a102="";
    private String a103="";
    private String a104="";
    private String a105="";
    private String a106="";
    private String a107="";
    private String a108="";
    private String a109="";
    private String a110="";
    private String a111="";
    private String a112="";
    private String a113="";
    private String a114="";
    private String a115="";
    private String a116="";
    private String a117="";
    private String a118="";
    private String a119="";
    private String a120="";
    private String b01="";
    private String b02="";
    private String b03="";
    private String b04="";
    private String b05="";
    private String b06="";
    private String b07="";
    private String b08="";
    private String b09="";
    private String b10="";
    private Double d01=0.0;
    private Double d02=0.0;
    private Double d03=0.0;
    private Double d04=0.0;
    private Double d05=0.0;
    private Double d06=0.0;
    private Double d07=0.0;
    private Double d08=0.0;
    private Double d09=0.0;
    private Double d10=0.0;
    private Double d11=0.0;
    private Double d12=0.0;
    private Double d13=0.0;
    private Double d14=0.0;
    private Double d15=0.0;
    private Double d16=0.0;
    private Double d17=0.0;
    private Double d18=0.0;
    private Double d19=0.0;
    private Double d20=0.0;
    private Double d21=0.0;
    private Double d22=0.0;
    private Double d23=0.0;
    private Double d24=0.0;
    private Double d25=0.0;
    private Double d26=0.0;
    private Double d27=0.0;
    private Double d28=0.0;
    private Double d29=0.0;
    private Double d30=0.0;

    @Column(name = "MEMO")
    private String memo;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "UPDATER")
    private String updater;

    @Column(name = "UPDATETIME")
    private String updatetime;

    @Column(name = "EFFECTIVE")
    private Integer effective;

    @Column(name = "CREATEDAY")
    private Date createday;

    @Column(name = "UPDATEDAY")
    private Date updateday;

    @Column(name = "UPDATECOUNT")
    private Double updatecount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getWf_id() {
        return wf_id;
    }

    public void setWf_id(BigInteger wf_id) {
        this.wf_id = wf_id;
    }

    public BigInteger getTrace_id() {
        return trace_id;
    }

    public void setTrace_id(BigInteger trace_id) {
        this.trace_id = trace_id;
    }

    public BigInteger getDynamic_instance_id() {
        return dynamic_instance_id;
    }

    public void setDynamic_instance_id(BigInteger dynamic_instance_id) {
        this.dynamic_instance_id = dynamic_instance_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getA01() {
        return a01;
    }

    public void setA01(String a01) {
        this.a01 = a01;
    }

    public String getA02() {
        return a02;
    }

    public void setA02(String a02) {
        this.a02 = a02;
    }

    public String getA03() {
        return a03;
    }

    public void setA03(String a03) {
        this.a03 = a03;
    }

    public String getA04() {
        return a04;
    }

    public void setA04(String a04) {
        this.a04 = a04;
    }

    public String getA05() {
        return a05;
    }

    public void setA05(String a05) {
        this.a05 = a05;
    }

    public String getA06() {
        return a06;
    }

    public void setA06(String a06) {
        this.a06 = a06;
    }

    public String getA07() {
        return a07;
    }

    public void setA07(String a07) {
        this.a07 = a07;
    }

    public String getA08() {
        return a08;
    }

    public void setA08(String a08) {
        this.a08 = a08;
    }

    public String getA09() {
        return a09;
    }

    public void setA09(String a09) {
        this.a09 = a09;
    }

    public String getA10() {
        return a10;
    }

    public void setA10(String a10) {
        this.a10 = a10;
    }

    public String getA11() {
        return a11;
    }

    public void setA11(String a11) {
        this.a11 = a11;
    }

    public String getA12() {
        return a12;
    }

    public void setA12(String a12) {
        this.a12 = a12;
    }

    public String getA13() {
        return a13;
    }

    public void setA13(String a13) {
        this.a13 = a13;
    }

    public String getA14() {
        return a14;
    }

    public void setA14(String a14) {
        this.a14 = a14;
    }

    public String getA15() {
        return a15;
    }

    public void setA15(String a15) {
        this.a15 = a15;
    }

    public String getA16() {
        return a16;
    }

    public void setA16(String a16) {
        this.a16 = a16;
    }

    public String getA17() {
        return a17;
    }

    public void setA17(String a17) {
        this.a17 = a17;
    }

    public String getA18() {
        return a18;
    }

    public void setA18(String a18) {
        this.a18 = a18;
    }

    public String getA19() {
        return a19;
    }

    public void setA19(String a19) {
        this.a19 = a19;
    }

    public String getA20() {
        return a20;
    }

    public void setA20(String a20) {
        this.a20 = a20;
    }

    public String getA21() {
        return a21;
    }

    public void setA21(String a21) {
        this.a21 = a21;
    }

    public String getA22() {
        return a22;
    }

    public void setA22(String a22) {
        this.a22 = a22;
    }

    public String getA23() {
        return a23;
    }

    public void setA23(String a23) {
        this.a23 = a23;
    }

    public String getA24() {
        return a24;
    }

    public void setA24(String a24) {
        this.a24 = a24;
    }

    public String getA25() {
        return a25;
    }

    public void setA25(String a25) {
        this.a25 = a25;
    }

    public String getA26() {
        return a26;
    }

    public void setA26(String a26) {
        this.a26 = a26;
    }

    public String getA27() {
        return a27;
    }

    public void setA27(String a27) {
        this.a27 = a27;
    }

    public String getA28() {
        return a28;
    }

    public void setA28(String a28) {
        this.a28 = a28;
    }

    public String getA29() {
        return a29;
    }

    public void setA29(String a29) {
        this.a29 = a29;
    }

    public String getA30() {
        return a30;
    }

    public void setA30(String a30) {
        this.a30 = a30;
    }

    public String getA31() {
        return a31;
    }

    public void setA31(String a31) {
        this.a31 = a31;
    }

    public String getA32() {
        return a32;
    }

    public void setA32(String a32) {
        this.a32 = a32;
    }

    public String getA33() {
        return a33;
    }

    public void setA33(String a33) {
        this.a33 = a33;
    }

    public String getA34() {
        return a34;
    }

    public void setA34(String a34) {
        this.a34 = a34;
    }

    public String getA35() {
        return a35;
    }

    public void setA35(String a35) {
        this.a35 = a35;
    }

    public String getA36() {
        return a36;
    }

    public void setA36(String a36) {
        this.a36 = a36;
    }

    public String getA37() {
        return a37;
    }

    public void setA37(String a37) {
        this.a37 = a37;
    }

    public String getA38() {
        return a38;
    }

    public void setA38(String a38) {
        this.a38 = a38;
    }

    public String getA39() {
        return a39;
    }

    public void setA39(String a39) {
        this.a39 = a39;
    }

    public String getA40() {
        return a40;
    }

    public void setA40(String a40) {
        this.a40 = a40;
    }

    public String getA41() {
        return a41;
    }

    public void setA41(String a41) {
        this.a41 = a41;
    }

    public String getA42() {
        return a42;
    }

    public void setA42(String a42) {
        this.a42 = a42;
    }

    public String getA43() {
        return a43;
    }

    public void setA43(String a43) {
        this.a43 = a43;
    }

    public String getA44() {
        return a44;
    }

    public void setA44(String a44) {
        this.a44 = a44;
    }

    public String getA45() {
        return a45;
    }

    public void setA45(String a45) {
        this.a45 = a45;
    }

    public String getA46() {
        return a46;
    }

    public void setA46(String a46) {
        this.a46 = a46;
    }

    public String getA47() {
        return a47;
    }

    public void setA47(String a47) {
        this.a47 = a47;
    }

    public String getA48() {
        return a48;
    }

    public void setA48(String a48) {
        this.a48 = a48;
    }

    public String getA49() {
        return a49;
    }

    public void setA49(String a49) {
        this.a49 = a49;
    }

    public String getA50() {
        return a50;
    }

    public void setA50(String a50) {
        this.a50 = a50;
    }

    public String getA51() {
        return a51;
    }

    public void setA51(String a51) {
        this.a51 = a51;
    }

    public String getA52() {
        return a52;
    }

    public void setA52(String a52) {
        this.a52 = a52;
    }

    public String getA53() {
        return a53;
    }

    public void setA53(String a53) {
        this.a53 = a53;
    }

    public String getA54() {
        return a54;
    }

    public void setA54(String a54) {
        this.a54 = a54;
    }

    public String getA55() {
        return a55;
    }

    public void setA55(String a55) {
        this.a55 = a55;
    }

    public String getA56() {
        return a56;
    }

    public void setA56(String a56) {
        this.a56 = a56;
    }

    public String getA57() {
        return a57;
    }

    public void setA57(String a57) {
        this.a57 = a57;
    }

    public String getA58() {
        return a58;
    }

    public void setA58(String a58) {
        this.a58 = a58;
    }

    public String getA59() {
        return a59;
    }

    public void setA59(String a59) {
        this.a59 = a59;
    }

    public String getA60() {
        return a60;
    }

    public void setA60(String a60) {
        this.a60 = a60;
    }

    public String getA61() {
        return a61;
    }

    public void setA61(String a61) {
        this.a61 = a61;
    }

    public String getA62() {
        return a62;
    }

    public void setA62(String a62) {
        this.a62 = a62;
    }

    public String getA63() {
        return a63;
    }

    public void setA63(String a63) {
        this.a63 = a63;
    }

    public String getA64() {
        return a64;
    }

    public void setA64(String a64) {
        this.a64 = a64;
    }

    public String getA65() {
        return a65;
    }

    public void setA65(String a65) {
        this.a65 = a65;
    }

    public String getA66() {
        return a66;
    }

    public void setA66(String a66) {
        this.a66 = a66;
    }

    public String getA67() {
        return a67;
    }

    public void setA67(String a67) {
        this.a67 = a67;
    }

    public String getA68() {
        return a68;
    }

    public void setA68(String a68) {
        this.a68 = a68;
    }

    public String getA69() {
        return a69;
    }

    public void setA69(String a69) {
        this.a69 = a69;
    }

    public String getA70() {
        return a70;
    }

    public void setA70(String a70) {
        this.a70 = a70;
    }

    public String getA71() {
        return a71;
    }

    public void setA71(String a71) {
        this.a71 = a71;
    }

    public String getA72() {
        return a72;
    }

    public void setA72(String a72) {
        this.a72 = a72;
    }

    public String getA73() {
        return a73;
    }

    public void setA73(String a73) {
        this.a73 = a73;
    }

    public String getA74() {
        return a74;
    }

    public void setA74(String a74) {
        this.a74 = a74;
    }

    public String getA75() {
        return a75;
    }

    public void setA75(String a75) {
        this.a75 = a75;
    }

    public String getA76() {
        return a76;
    }

    public void setA76(String a76) {
        this.a76 = a76;
    }

    public String getA77() {
        return a77;
    }

    public void setA77(String a77) {
        this.a77 = a77;
    }

    public String getA78() {
        return a78;
    }

    public void setA78(String a78) {
        this.a78 = a78;
    }

    public String getA79() {
        return a79;
    }

    public void setA79(String a79) {
        this.a79 = a79;
    }

    public String getA80() {
        return a80;
    }

    public void setA80(String a80) {
        this.a80 = a80;
    }

    public String getA81() {
        return a81;
    }

    public void setA81(String a81) {
        this.a81 = a81;
    }

    public String getA82() {
        return a82;
    }

    public void setA82(String a82) {
        this.a82 = a82;
    }

    public String getA83() {
        return a83;
    }

    public void setA83(String a83) {
        this.a83 = a83;
    }

    public String getA84() {
        return a84;
    }

    public void setA84(String a84) {
        this.a84 = a84;
    }

    public String getA85() {
        return a85;
    }

    public void setA85(String a85) {
        this.a85 = a85;
    }

    public String getA86() {
        return a86;
    }

    public void setA86(String a86) {
        this.a86 = a86;
    }

    public String getA87() {
        return a87;
    }

    public void setA87(String a87) {
        this.a87 = a87;
    }

    public String getA88() {
        return a88;
    }

    public void setA88(String a88) {
        this.a88 = a88;
    }

    public String getA89() {
        return a89;
    }

    public void setA89(String a89) {
        this.a89 = a89;
    }

    public String getA90() {
        return a90;
    }

    public void setA90(String a90) {
        this.a90 = a90;
    }

    public String getA91() {
        return a91;
    }

    public void setA91(String a91) {
        this.a91 = a91;
    }

    public String getA92() {
        return a92;
    }

    public void setA92(String a92) {
        this.a92 = a92;
    }

    public String getA93() {
        return a93;
    }

    public void setA93(String a93) {
        this.a93 = a93;
    }

    public String getA94() {
        return a94;
    }

    public void setA94(String a94) {
        this.a94 = a94;
    }

    public String getA95() {
        return a95;
    }

    public void setA95(String a95) {
        this.a95 = a95;
    }

    public String getA96() {
        return a96;
    }

    public void setA96(String a96) {
        this.a96 = a96;
    }

    public String getA97() {
        return a97;
    }

    public void setA97(String a97) {
        this.a97 = a97;
    }

    public String getA98() {
        return a98;
    }

    public void setA98(String a98) {
        this.a98 = a98;
    }

    public String getA99() {
        return a99;
    }

    public void setA99(String a99) {
        this.a99 = a99;
    }

    public String getA100() {
        return a100;
    }

    public void setA100(String a100) {
        this.a100 = a100;
    }

    public String getA101() {
        return a101;
    }

    public void setA101(String a101) {
        this.a101 = a101;
    }

    public String getA102() {
        return a102;
    }

    public void setA102(String a102) {
        this.a102 = a102;
    }

    public String getA103() {
        return a103;
    }

    public void setA103(String a103) {
        this.a103 = a103;
    }

    public String getA104() {
        return a104;
    }

    public void setA104(String a104) {
        this.a104 = a104;
    }

    public String getA105() {
        return a105;
    }

    public void setA105(String a105) {
        this.a105 = a105;
    }

    public String getA106() {
        return a106;
    }

    public void setA106(String a106) {
        this.a106 = a106;
    }

    public String getA107() {
        return a107;
    }

    public void setA107(String a107) {
        this.a107 = a107;
    }

    public String getA108() {
        return a108;
    }

    public void setA108(String a108) {
        this.a108 = a108;
    }

    public String getA109() {
        return a109;
    }

    public void setA109(String a109) {
        this.a109 = a109;
    }

    public String getA110() {
        return a110;
    }

    public void setA110(String a110) {
        this.a110 = a110;
    }

    public String getA111() {
        return a111;
    }

    public void setA111(String a111) {
        this.a111 = a111;
    }

    public String getA112() {
        return a112;
    }

    public void setA112(String a112) {
        this.a112 = a112;
    }

    public String getA113() {
        return a113;
    }

    public void setA113(String a113) {
        this.a113 = a113;
    }

    public String getA114() {
        return a114;
    }

    public void setA114(String a114) {
        this.a114 = a114;
    }

    public String getA115() {
        return a115;
    }

    public void setA115(String a115) {
        this.a115 = a115;
    }

    public String getA116() {
        return a116;
    }

    public void setA116(String a116) {
        this.a116 = a116;
    }

    public String getA117() {
        return a117;
    }

    public void setA117(String a117) {
        this.a117 = a117;
    }

    public String getA118() {
        return a118;
    }

    public void setA118(String a118) {
        this.a118 = a118;
    }

    public String getA119() {
        return a119;
    }

    public void setA119(String a119) {
        this.a119 = a119;
    }

    public String getA120() {
        return a120;
    }

    public void setA120(String a120) {
        this.a120 = a120;
    }

    public String getB01() {
        return b01;
    }

    public void setB01(String b01) {
        this.b01 = b01;
    }

    public String getB02() {
        return b02;
    }

    public void setB02(String b02) {
        this.b02 = b02;
    }

    public String getB03() {
        return b03;
    }

    public void setB03(String b03) {
        this.b03 = b03;
    }

    public String getB04() {
        return b04;
    }

    public void setB04(String b04) {
        this.b04 = b04;
    }

    public String getB05() {
        return b05;
    }

    public void setB05(String b05) {
        this.b05 = b05;
    }

    public String getB06() {
        return b06;
    }

    public void setB06(String b06) {
        this.b06 = b06;
    }

    public String getB07() {
        return b07;
    }

    public void setB07(String b07) {
        this.b07 = b07;
    }

    public String getB08() {
        return b08;
    }

    public void setB08(String b08) {
        this.b08 = b08;
    }

    public String getB09() {
        return b09;
    }

    public void setB09(String b09) {
        this.b09 = b09;
    }

    public String getB10() {
        return b10;
    }

    public void setB10(String b10) {
        this.b10 = b10;
    }

    public Double getD01() {
        return d01;
    }

    public void setD01(Double d01) {
        this.d01 = d01;
    }

    public Double getD02() {
        return d02;
    }

    public void setD02(Double d02) {
        this.d02 = d02;
    }

    public Double getD03() {
        return d03;
    }

    public void setD03(Double d03) {
        this.d03 = d03;
    }

    public Double getD04() {
        return d04;
    }

    public void setD04(Double d04) {
        this.d04 = d04;
    }

    public Double getD05() {
        return d05;
    }

    public void setD05(Double d05) {
        this.d05 = d05;
    }

    public Double getD06() {
        return d06;
    }

    public void setD06(Double d06) {
        this.d06 = d06;
    }

    public Double getD07() {
        return d07;
    }

    public void setD07(Double d07) {
        this.d07 = d07;
    }

    public Double getD08() {
        return d08;
    }

    public void setD08(Double d08) {
        this.d08 = d08;
    }

    public Double getD09() {
        return d09;
    }

    public void setD09(Double d09) {
        this.d09 = d09;
    }

    public Double getD10() {
        return d10;
    }

    public void setD10(Double d10) {
        this.d10 = d10;
    }

    public Double getD11() {
        return d11;
    }

    public void setD11(Double d11) {
        this.d11 = d11;
    }

    public Double getD12() {
        return d12;
    }

    public void setD12(Double d12) {
        this.d12 = d12;
    }

    public Double getD13() {
        return d13;
    }

    public void setD13(Double d13) {
        this.d13 = d13;
    }

    public Double getD14() {
        return d14;
    }

    public void setD14(Double d14) {
        this.d14 = d14;
    }

    public Double getD15() {
        return d15;
    }

    public void setD15(Double d15) {
        this.d15 = d15;
    }

    public Double getD16() {
        return d16;
    }

    public void setD16(Double d16) {
        this.d16 = d16;
    }

    public Double getD17() {
        return d17;
    }

    public void setD17(Double d17) {
        this.d17 = d17;
    }

    public Double getD18() {
        return d18;
    }

    public void setD18(Double d18) {
        this.d18 = d18;
    }

    public Double getD19() {
        return d19;
    }

    public void setD19(Double d19) {
        this.d19 = d19;
    }

    public Double getD20() {
        return d20;
    }

    public void setD20(Double d20) {
        this.d20 = d20;
    }

    public Double getD21() {
        return d21;
    }

    public void setD21(Double d21) {
        this.d21 = d21;
    }

    public Double getD22() {
        return d22;
    }

    public void setD22(Double d22) {
        this.d22 = d22;
    }

    public Double getD23() {
        return d23;
    }

    public void setD23(Double d23) {
        this.d23 = d23;
    }

    public Double getD24() {
        return d24;
    }

    public void setD24(Double d24) {
        this.d24 = d24;
    }

    public Double getD25() {
        return d25;
    }

    public void setD25(Double d25) {
        this.d25 = d25;
    }

    public Double getD26() {
        return d26;
    }

    public void setD26(Double d26) {
        this.d26 = d26;
    }

    public Double getD27() {
        return d27;
    }

    public void setD27(Double d27) {
        this.d27 = d27;
    }

    public Double getD28() {
        return d28;
    }

    public void setD28(Double d28) {
        this.d28 = d28;
    }

    public Double getD29() {
        return d29;
    }

    public void setD29(Double d29) {
        this.d29 = d29;
    }

    public Double getD30() {
        return d30;
    }

    public void setD30(Double d30) {
        this.d30 = d30;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getEffective() {
        return effective;
    }

    public void setEffective(Integer effective) {
        this.effective = effective;
    }

    public Date getCreateday() {
        return createday;
    }

    public void setCreateday(Date createday) {
        this.createday = createday;
    }

    public Date getUpdateday() {
        return updateday;
    }

    public void setUpdateday(Date updateday) {
        this.updateday = updateday;
    }

    public Double getUpdatecount() {
        return updatecount;
    }

    public void setUpdatecount(Double updatecount) {
        this.updatecount = updatecount;
    }
}