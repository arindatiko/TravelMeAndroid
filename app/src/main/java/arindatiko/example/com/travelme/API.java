package arindatiko.example.com.travelme;

import arindatiko.example.com.travelme.model.Drivers;
import arindatiko.example.com.travelme.model.Kuliner;
import arindatiko.example.com.travelme.model.Penginapan;
import arindatiko.example.com.travelme.model.User;
import arindatiko.example.com.travelme.model.Wisata;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class API {
    //public static String BASE_URL = "http://192.168.100.3/travel_me/index.php/Api/";
    //  public static String BASE_URL = "http://192.168.43.47/travel_me/index.php/Api/";
    public static String BASE_URL = "http://arinda.jagopesan.com/travel_me/index.php/Api/";

    public static PostService service_post = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(API.PostService.class);

    public static GetService service_get = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(API.GetService.class);

    public static DeleteService service_delete = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(API.DeleteService.class);

    public interface PostService{
        @FormUrlEncoded
        @POST("login")
        Call<User> login(
                @Field("no_telp") String no_telp,
                @Field("password") String pass
                /*@Field("user_type") String user_type*/
        );

        @FormUrlEncoded
        @POST("kuliner_update")
        Call<Kuliner> kuliner_update(
                @Field("id_kuliner") int id_kuliner,
                @Field("nama")String nama,
                @Field("alamat") String alamat,
                @Field("no_telp") String no_telp,
                @Field("deskripsi") String deskripsi,
                @Field("akses") String akses,
                @Field("fasilitas") String fasilitas,
                @Field("posisi_lat") Double posisi_lat,
                @Field("posisi_lng") Double posisi_lng,
                @Field("jam_buka") String jam_buka,
                @Field("jam_tutup") String jam_tutup
        );

        @FormUrlEncoded
        @POST("wisata_update")
        Call<Wisata> wisata_update(
                @Field("id_wisata") int id_wisata,
                @Field("nama")String nama,
                @Field("alamat") String alamat,
                @Field("deskripsi") String deskripsi,
                @Field("akses") String akses,
                @Field("jenis") String jenis,
                @Field("fasilitas") String fasilitas,
                @Field("posisi_lat") Double posisi_lat,
                @Field("posisi_lng") Double posisi_lng,
                @Field("jam_buka") String jam_buka,
                @Field("jam_tutup") String jam_tutup
        );

        @FormUrlEncoded
        @POST("penginapan_update")
        Call<Penginapan> penginapan_update(
                @Field("id_penginapan") int id_penginapan,
                @Field("nama")String nama,
                @Field("alamat") String alamat,
                @Field("no_telp") String no_telp,
                @Field("deskripsi") String deskripsi,
                @Field("akses") String akses,
                @Field("fasilitas") String fasilitas,
                @Field("posisi_lat") Double posisi_lat,
                @Field("posisi_lng") Double posisi_lng
        );

        @FormUrlEncoded
        @POST("detail_kuliner_admin")
        Call<Kuliner> detail_kuliner_admin(@Field("id_user") String id_user);

        @FormUrlEncoded
        @POST("detail_wisata_admin")
        Call<Wisata> get_detail_wisata(@Field("id_user") String id_user);

        @FormUrlEncoded
        @POST("detail_penginapan_admin")
        Call<Penginapan> get_detail_penginapan(@Field("id_user") String id_user);

        @FormUrlEncoded
        @POST("user")
        Call<Drivers> get_user(@Field("id_user") int id_user);
    }

    public interface GetService{

    }

    public interface DeleteService{

    }
}
