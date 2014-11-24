package device;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
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
	
	/** The constant for petunjuk arah. */
	final int EXIT = 4;
	
	/*
	 * fungsi untuk memanggil halaman depan dari aplikasi Location-Aware Tour Guide
	 * 
	 */
	public void toMainMenu(ContextManager contextManagerService,BundleContext bundleContext,BufferedReader reader, GPS gps, ServiceReference contextmanagerServiceReference, String message) throws IOException
	{
		if(contextManagerService.getFlag().equals("init")){
			
			while(!contextManagerService.isValidUser()){
				System.out.println("Masukkan nama mu!");
				String user = reader.readLine();
				contextManagerService.checkValidUser(user);
			}
			contextManagerService.setFlag("menu");
			toMainMenu(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference,"" );
		}
		else if(contextManagerService.getFlag().equals("menu"))
		{
			System.out.println("Hai "+contextManagerService.getValidUser());
			System.out.println("Selamat datang di Location-Aware Tour Guide");
			System.out.println("Kamu sekarang berada di " + contextManagerService.getCurrentLocationPosition());
			System.out.println("");
			System.out.println("Silahkan masukkan pilihan anda:");
			System.out.println("1. Cari informasi mengenai sebuah tempat menarik");
			System.out.println("2. Cari tempat menarik di lokasi sekarang");
			System.out.println("3. Berikan petunjuk arah menuju sebuah tempat");
			System.out.println("4. Exit");	
			
			int mode = Integer.parseInt(reader.readLine().trim());
			
			if (mode == INFO_TEMPAT_MENARIK) {
				System.out.print("Masukkan nama tempat yang ingin dicari informasinya: ");
				String namatempat = reader.readLine();
				System.out.println(contextManagerService.getByName(namatempat));
				enableBackButton(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference);
			} else if (mode == TEMPAT_MENARIK_LOKASI_SKRG) {
				//flag menusuper digunakan agar preference place tidak dapat keluar setelah
				//memilih salah satu fungsi pada menu utama
				ArrayList<PlaceOfInterest> pois = gps.getCurrentLocationPOI(
						bundleContext, contextmanagerServiceReference);
	
				for (int i = 0; i < pois.size(); i++) {
					System.out
							.printf("%d NAMA: %s\n", i + 1, pois.get(i).getName());
				}
				
				System.out.println("pilih poi: ");
				int poiSelected = Integer.parseInt(reader.readLine().trim()) - 1;

				PlaceOfInterest poi = pois.get(poiSelected);

				System.out.println(poi.toString());
				enableBackButton(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference);
				
			} else if (mode == PETUNJUK_ARAH) {
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
				
				enableBackButton(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference);
			}
			else if(mode == EXIT){
				contextManagerService.setFlag("idle");
				toMainMenu(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference,"" );
			}
		}
		//Jika ternyata sudah ada perubahan flag, sehingga yang diproses adalah preference
		//getSuggestedPlace akan menghasilkan hasil karena sudah di set pada context manager
		else if(contextManagerService.getFlag().equals("preference"))
		{
			int mode = 0;
			if(message!=""){
				mode = Integer.parseInt(message.trim());
			}
			else{
				mode = Integer.parseInt(reader.readLine().trim());
			}
			String[] suggested =contextManagerService.getSuggestedPlace();
			PlaceOfInterest tempatnya = contextManagerService.getByName(suggested[mode-1]);
			System.out.println(tempatnya.getInformation());
			contextManagerService.setFlag("idle");
			enableBackButton(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference);

		}
		else{
			System.out.println("System in idle mode now");
			System.out.println("Press any key to enter main menu");
			String pressedOnly = reader.readLine();
			if(contextManagerService.getFlag().equals("idle")){
				contextManagerService.setFlag("menu");
			}
			toMainMenu(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference, pressedOnly);
		}
		
		
	
	}

	public void enableBackButton(ContextManager contextManagerService,BundleContext bundleContext,BufferedReader reader, GPS gps, ServiceReference contextmanagerServiceReference) throws IOException{
		System.out.println("");
		System.out.println("Tekan 'B' untuk kembali ke menu utama");
		String pilihan = reader.readLine();
		contextManagerService.setFlag("menu");
		if(pilihan.equalsIgnoreCase("B"))
		{
			//menampilkan kembali menu utama
			toMainMenu(contextManagerService,bundleContext,reader, gps,contextmanagerServiceReference,"");
		}
	}
	
}
