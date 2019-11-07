package codingame;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Defibrillators {

	private static final Scanner IN = new Scanner(System.in);

	public static void main(String args[]) {
		String longitude = IN.next();
		String latitude = IN.next();
		int numberOfDefibrillators = IN.nextInt();
		if (IN.hasNextLine()) {
			IN.nextLine();
		}

		final List<Defibrillator> defibrillators = IntStream.rangeClosed(1, numberOfDefibrillators)
				.mapToObj(value -> Defibrillator.parseDefibrillator(IN.nextLine()))
				.collect(Collectors.toList());

		final String closestDefibrillatorNames = getClosestDefibrillators(longitude, latitude, defibrillators)
				.stream()
				.map(Defibrillator::getName)
				.collect(Collectors.joining("\n"));
		System.out.println(closestDefibrillatorNames);
	}

	private static List<Defibrillator> getClosestDefibrillators(String longitude, String latitude,
			List<Defibrillator> defibrillators) {
		return getDefibrillatorsByDistance(longitude, latitude, defibrillators)
				.entrySet().stream()
				.min(Comparator.comparing(Map.Entry::getKey))
				.orElse(new AbstractMap.SimpleEntry<>(Double.MAX_VALUE, Collections.emptyList()))
				.getValue();
	}

	private static Map<Double, List<Defibrillator>> getDefibrillatorsByDistance(String longitude, String latitude,
			List<Defibrillator> defibrillators) {
		return defibrillators.stream()
				.collect(Collectors.groupingBy(defibrillator -> defibrillator.getDistanceFrom(longitude, latitude)));
	}

	private static class Defibrillator {

		private int id;
		private String name;
		private String address;
		private String contact;
		private Float longitude;
		private Float latitude;

		Defibrillator(int id, String name, String address, String contact, Float longitude, Float latitude) {
			this.id = id;
			this.name = name;
			this.address = address;
			this.contact = contact;
			this.longitude = longitude;
			this.latitude = latitude;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getAddress() {
			return address;
		}

		public String getContact() {
			return contact;
		}

		public Float getLongitude() {
			return longitude;
		}

		public Float getLatitude() {
			return latitude;
		}

		static Defibrillator parseDefibrillator(String string) {
			final String[] splitString = string.split(";");
			return new Defibrillator(Integer.parseInt(splitString[0]), splitString[1], splitString[2], splitString[3],
					Float.parseFloat(splitString[4].replace(",", ".")), Float.parseFloat(splitString[5].replace(",", ".")));
		}

		double getDistanceFrom(String longitude, String latitude) {
			final float longitudeFloat = Float.parseFloat(longitude.replace(",", "."));
			final float latitudeFloat = Float.parseFloat(latitude.replace(",", "."));
			final double x = (longitudeFloat - getLongitude()) * Math.cos((getLatitude() + latitudeFloat) / 2);
			final float y = latitudeFloat - getLatitude();
			return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * 6371;
		}
	}
}
