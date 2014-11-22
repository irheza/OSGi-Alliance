package device;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import contextmanager.ContextManager;
import entity.PlaceOfInterest;
import gps.GPS;

public class MainMenu {
	/** The constant for tempat menarik. */
	final int INFO_TEMPAT_MENARIK = 1;
	
	/** The constant for tempat menarik lokasi skrg. */
	final int TEMPAT_MENARIK_LOKASI_SKRG = 2;
	
	/** The constant for petunjuk arah. */
	final int PETUNJUK_ARAH = 3;
	/*
	 * fungsi untuk memanggil halaman depan dari aplikasi Location-Aware Tour Guide
	 * 
	 */
	public void toMainMenu(ContextManager contextManagerService,BundleContext bundleContext,BufferedReader reader, GPS gps, ServiceReference contextmanagerServiceReference) throws IOException
	{
		System.out.println("Selamat datang di Location-Aware Tour Guide");
		System.out.println("");
		System.out.println("Silahkan masukkan pilihan anda:");
		System.out.println("1. Cari informasi mengenai sebuah tempat menarik");
		System.out.println("2. Cari tempat menarik di lokasi sekarang");
		System.out.println("3. Berikan petunjuk arah menuju sebuah tempat");
		System.out.println("E. Exit");	
		contextManagerService.setFlag("menu");
		int mode = Integer.parseInt(reader.readLine().trim());
	
		if(contextManagerService.getFlag().equals("menu"))
		{
			if (mode == INFO_TEMPAT_MENARIK) {
				contextManagerService.setFlag("menusuper");
				System.out.print("Masukkan nama tempat yang ingin dicari informasinya: ");
				String namatempat = reader.readLine();
				System.out.println(contextManagerService.getByName(namatempat));
			} else if (mode == TEMPAT_MENARIK_LOKASI_SKRG) {
				//flag menusuper digunakan agar preference place tidak dapat keluar setelah
				//memilih salah satu fungsi pada menu utama
				contextManagerService.setFlag("menusuper");
				ArrayList<PlaceOfInterest> pois = gps.getCurrentLocationPOI(
						bundleContext, contextmanagerServiceReference);
	
				for (int i = 0; i < pois.size(); i++) {
					System.out
							.printf("%d NAMA: %s\n", i + 1, pois.get(i).getName());
	
	
					System.out.println("pilih poi: ");
					int poiSelected = Integer.parseInt(reader.readLine().trim()) - 1;
	
					PlaceOfInterest poi = pois.get(poiSelected);
	
					System.out.println(poi.toString());
				}
			} else if (mode == PETUNJUK_ARAH) {
				contextManagerService.setFlag("menusuper");
				System.out.println("sebut nama tempat yang mau dicari:");
	
				String toStr = reader.readLine();
				
				String cd = gps.getCompassDirective(bundleContext,
						contextmanagerServiceReference, toStr);
				if (cd != null) {
					//jika tempat yang dicari berada di posisi anda saat ini
					if(cd.equals("POSISI SAMA"))
					{
						System.out.println(toStr+" berada di lokasi anda sekarang.");
					}
					else
					{
						System.out.println(toStr+" berada di "+cd+ " dari lokasi anda sekarang.");
					}
				} else {
					System.out.println("lokasi tidak ditemukan");
				}
			}
			System.out.println("Tekan 'B' untuk kembali ke menu utama");
			String pilihan = reader.readLine();
			if(pilihan.equalsIgnoreCase("B"))
			{
				toMainMenu(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference );
			}
		}
		//Jika ternyata sudah ada perubahan flag, sehingga yang diproses adalah preference
		//getSuggestedPlace akan menghasilkan hasil karena sudah di set pada context manager
		else
		{
			String[] suggested =contextManagerService.getSuggestedPlace();
			PlaceOfInterest tempatnya = contextManagerService.getByName(suggested[mode-1]);
			System.out.println(tempatnya.getInformation());
			System.out.println("Tekan 'B' untuk kembali ke menu utama");
			String pilihan = reader.readLine();
			if(pilihan.equalsIgnoreCase("B"))
			{
				//menampilkan kembali menu utama
				toMainMenu(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference );
			}

		}
	
	
	}
}
