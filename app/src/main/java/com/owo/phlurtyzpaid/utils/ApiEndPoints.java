package com.owo.phlurtyzpaid.utils;

import com.owo.phlurtyzpaid.BuildConfig;

/**
 * Created by kibrom on 6/21/17.
 */

public class ApiEndPoints {

    public static final String HOST = BuildConfig.URL;

    public static final String UPLOAD_DIRECTORY ="/admin/uploads/advertisment/";

    public static final String ADVERTISMENT ="/admin/api/web/index.php/api/v1";

    public static final String PPC ="/admin/api/web/index.php/api/v1";

    public static final String SPECIALS_BASE_URL = HOST;

    public static final String CategoryPaid = SPECIALS_BASE_URL + "api/category/getPaidCategories";

    public static final String CategoryAll = SPECIALS_BASE_URL + "api/category/getAll";

    public static final String CategoryById = SPECIALS_BASE_URL + "api/emoji/getByCatId/";

    public static final String PaymentURL = "https://www.diodot.com/phlurtyzgatewayFreeApp/payinapp.php";
}
