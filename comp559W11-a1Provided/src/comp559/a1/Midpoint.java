package comp559.a1;

import java.util.ArrayList;

public class Midpoint implements Integrator {
	
	double [] dydt;
	double [] y_midpoint;
	//This int checks that if the dimension did change (which is a rare occurrence event).
	int old_n = -1;
	
	
    @Override
    public String getName() {
        return "midpoint";
    }
    
    @Override
    public void step(double[] y, int n, double t, double h, double[] yout, Function derivs) {
        // TODO: implement this method
    	
        //Avoid useless memory allocation if size did not change
    	if (old_n!=n) {
        	dydt = new double[n];
        	y_midpoint = new double[n];
        	old_n = n;
    	}
    	
        //delta_x = h*f(x(t),t)
        derivs.derivs(t, y, dydt);
        
        //X = ((x(t)+delta_x)/2
        for (int i = 0 ; i < n ; i++) {
        	dydt[i] = (y[i] + h*dydt[i])/2;
        }
        
        //f_mid = f(X,t)
        derivs.derivs(t,dydt,y_midpoint);
        
        //Compute final output. x(t+delta_t) = x(t) + delta_t*f_mid
        for (int i = 0 ; i < n ; i++) {
        	yout[i] = y[i] + h*y_midpoint[i];
        }
        
    }

}