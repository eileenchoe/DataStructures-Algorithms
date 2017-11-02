class ClockSolver1 {
	public static void main (String[]args) {
		double slice = 60;
		double sliceAngle = 0;
		double testAngle = 180;
		Clock c = new Clock();
		try {
			slice = Double.parseDouble(args[0]);
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("Time slice set to default, 60s");
		}

		if (slice > 1800 || slice < 0 || testAngle <0 || testAngle > 360) {
			slice = 60.0;
			System.out.println("Invalid input, time slice set to default, 60s");
		}
		
		sliceAngle = (slice*.1-((1.0/12.0)*slice*0.1));

		final String DEGREE  = "\u00b0";
		System.out.println("\nThe clock is " + testAngle  + DEGREE + " at: ");

		while(!c.isOver()) {
			c.tick(slice, sliceAngle);
			if (c.isInternalAngle(sliceAngle, testAngle)) {
				System.out.println(c.getTime());
			}
			else if (c.isExternalAngle(sliceAngle, testAngle)) {
				System.out.println(c.getTime());
			}
		}
	}
}