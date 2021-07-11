package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkin, Date checkout){
		Date now = new Date();
		if(checkin.before(now) || checkout.before(now)) throw new DomainException("Reservation dates for update must be future dates");
		if(!checkout.after(checkin)) throw new DomainException("Check-out date must be after check-in date");
		
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}
	
	public long duration() {
		long diff = this.checkout.getTime() - this.checkin.getTime(); //getTime retorna a data em milisegundos
		
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //converte o diff que está em milisegundos em dias
	}
	
	public void updateDates(Date checkin, Date checkout) {
		Date now = new Date();
		
		if(checkin.before(now) || checkout.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		
		if(!checkout.after(checkin)){
			throw new DomainException("Check-out date must be after check-in date");
		}
		
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		return str
			.append("Reservation: Room ")
			.append(this.roomNumber)
			.append(", checkin: ")
			.append(sdf.format(this.checkin))
			.append(", checkout: ")
			.append(sdf.format(this.checkout))
			.append(", ")
			.append(this.duration())
			.append(" nights")
			.toString();
	}
}
