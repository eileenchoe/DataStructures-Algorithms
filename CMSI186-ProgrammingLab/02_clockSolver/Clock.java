class Clock {

	private double hours;
	private double min;
	private double seconds;
	private double secondsCounter;
	private double angle;
	
	public Clock() {
		this.hours = 0;
		this.min = 0;
		this.seconds = 0;
		this.secondsCounter = 0;
		this.angle = 0;
	}

	public void tick (double slice, double sliceAngle) {
		this.secondsCounter += slice;
		this.hours = Math.floor(this.secondsCounter / 3600);
		this.min = Math.floor((this.secondsCounter % 3600) / 60);
		this.seconds = Math.floor((this.secondsCounter % 3600) % 60);
		this.angle = (this.angle + sliceAngle) % 360;
	}

	public String getTime() {
		return String.format("%02d",Math.round(this.hours)) + ":" + String.format("%02d",Math.round(this.min)) + ":" +  String.format("%02d",Math.round(this.seconds)) ;
	}

	public boolean isInternalAngle(double sliceAngle, double testAngle) {
		return (this.angle < (testAngle + sliceAngle / 2)) && (this.angle > (testAngle - sliceAngle / 2));
	}

	public boolean isExternalAngle(double sliceAngle, double testAngle) {
		return ((360-this.angle) < (testAngle + sliceAngle / 2)) && ((360-this.angle) > (testAngle - sliceAngle / 2));
	}

	public boolean isOver () {
		return this.secondsCounter >= (3600 * 12);
	}

}
