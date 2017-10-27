import java.util.ArrayList;
public class Partida {
	private int x, xWum, yWum;
    private Habitacion[][] tablero;
    private Jugador jugador;
    private ArrayList<Agujero> agujeros;

    // MÉTODO CONSTRUCTOR:
    /**
     * Constructor que dado un tamaño crea las condiciones para la partida que se usará para jugar al juego. 
     * @param x int, que representa el tamaño (NxN) del tablero.
     * @return Partida, la Partida creada.
     */
    public Partida(int x){
        this.x = x;
        this.tablero = prepararTablero(x);
        this.jugador = new Jugador(0,0);
        this.agujeros = new ArrayList<Agujero>();
        prepararGold();
        prepararWumpus();
        prepararAgujeros();
        prepararHedor(xWum, yWum);
    }    

    // MÉTODOS CONSULTORES (GETTERS):
    /**
     * Método consultor que devuelve el tamaño del tablero de la partida.
     * @return int, el tamaño del tablero.
     */
    public int getX(){
        return x;
    }
    
    /**
     * Método consultor que devuelve la coordenada en el eje horizontal del Wumpus.
     * @return int, la coordenada.
     */
    public int getXWum(){
        return xWum;
    }
    
    /**
     * Método consultor que devuelve la coordenada en el eje vertical del Wumpus.
     * @return int, la coordenada.
     */
    public int getYWum(){
        return yWum;
    }
    
    /**
     * Método consultor que devuelve el tablero de juego.
     * @return Habitacion[][], el tablero de juego.
     */
    public Habitacion[][] getHabitacion(){
        return tablero;
    }

    /**
     * Método consultor que devuelve el jugador asociado a la partida.
     * @return Jugador, el jugador en cuestión.
     */
    public Jugador getJugador(){
        return jugador;
    }

    /**
     * Método consultor que devuelve un ArrayList con los agujeros presentes en el tablero de juego.
     * @return ArrayList<Agujero>, la lista con los agujeros presentes en el tablero.
     */
    public ArrayList<Agujero> getAgujeros(){
        return agujeros;
    }
    
    // MÉTODOS MODIFICADORES:
    /**
     * Método que modifica el tamaño del tablero.
     * @param x int, que representa el nuevo tamaño del tablero.
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * Método que modifica la coordenada del eje horizontal del Wumpus.
     * @param x int, que representa a la nueva coordenada del eje horizontal del Wumpus.
     */
    public void setXWumpus(int xWumpus){
        this.xWum = xWumpus;
    }
    
    /**
     * Método que modifica la coordenada del eje vertical del Wumpus.
     * @param x int, que representa a la nueva coordenada del eje vertical del Wumpus.
     */
    public void setYWumpus(int yWumpus){
        this.yWum = yWumpus;
    }
    
    /**
     * Método que modifica el tablero de juego por otro.
     * @param tablero Habitacion[][], que representa al nuevo tablero.
     */
    public void setHabitacion(Habitacion[][] tablero){
        this.tablero = tablero;
    }
    
    /**
     * Método que modifica el jugador por uno nuevo.
     * @param jugador Jugador, que representa el nuevo jugador.
     */
    public void setJugador(Jugador jugador){
        this.jugador = jugador;
    }
    
    /**
     * Método que modifica la lista de agujeros presentes en el tablero por una nueva.
     * @param agujeros ArrayList<Agujero>, que representa a la nueva lista de agujeros.
     */
    public void setAgujeros(ArrayList<Agujero> agujeros){
        this.agujeros = agujeros;
    }
    
    // MÉTODOS FUNCIONALES:
    /**
     * Método consultor que devuelve la habitación dadas unas ciertas coordenadas.
     * @param x int, que representa la coordenada de la habitación en el eje horizontal.
     * @param y int, que representa la coordenada de la habitación en el eje vertical.
     * @return Habitacion, la habitación en cuestión.
     */
    public Habitacion getHabitacion(int x, int y){
        Habitacion Habitacion = tablero[x][y];
        return Habitacion;
    }
    
    /**
     * Método que se encarga de la creación del tablero del juego.
     * @param n int, que representa el tamaño del tablero (NxN).
     * @return Habitacion[][], el tablero una vez creado.
     */
    public Habitacion[][] prepararTablero(int n){
        Habitacion[][] tablero = new Habitacion[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                tablero[i][j] = new Habitacion(i,j);
            }
        }
        return tablero;
    }

    /**
     * Método que se encarga de colocar el oro en una casilla del tablero de manera aleatoria.
     */
    public void prepararGold(){
        int xGold, yGold;
        do{
            xGold = (int) (Math.random() * x);
            yGold = (int) (Math.random() * x); 
        } while(xGold == jugador.getX() && yGold == jugador.getY()); 
        Habitacion Habitacion = getHabitacion(xGold, yGold);
        Habitacion.setHayOro(true);
    }    

    /**
     * Método que se encarga de colocar el Wumpus en una casilla del tablero de manera aleatoria.
     */
    public void prepararWumpus(){
        int xWumpus, yWumpus;
        do{
            xWumpus = (int) (Math.random() * x);
            yWumpus = (int) (Math.random() * x); 
        } while(xWumpus == 0 && yWumpus == 0); 
        Habitacion Habitacion = getHabitacion(xWumpus, yWumpus);
        Habitacion.setHayWumpus(true);
        xWum = xWumpus;
        yWum = yWumpus;
    }

    /**
     * Método que se encarga de colocar diversos agujeros en casillas del tablero de manera aleatoria.
     */
    public void prepararAgujeros(){
        for(int i = 0; i < (x-1); i++){
            int xAgujero, yAgujero;
            do{
                xAgujero = (int) (Math.random() * x);
                yAgujero = (int) (Math.random() * x);
            }while(!habitacionLibre(xAgujero,yAgujero));
            Agujero Agujero = new Agujero(xAgujero, yAgujero);
            agujeros.add(Agujero);
            Habitacion Habitacion = getHabitacion(xAgujero, yAgujero);
            Habitacion.setHayAgujero(true);
            prepararBrisa(xAgujero, yAgujero);
        }
    }

    /**
     * Método que se encarga de colocar la condición de brisa en las casillas adyacentes a los agujeros.
     * @param xAgujero int, que representa la coordenada en el eje horizontal del agujero en cuestión.
     * @param yAgujero int, que representa la coordenada en el eje vertical del agujero en cuestión.
     */
    public void prepararBrisa(int xAgujero, int yAgujero){
        ArrayList<Habitacion> Habitacions = new ArrayList<>();
        // Arriba del agujero:
        if((xAgujero - 1) >= 0){
            Habitacion HabitacionSuperior = getHabitacion(xAgujero - 1, yAgujero);
            Habitacions.add(HabitacionSuperior);
        }
        // Abajo del agujero:
        if((xAgujero + 1) < x){
            Habitacion HabitacionInferior = getHabitacion(xAgujero + 1, yAgujero);
            Habitacions.add(HabitacionInferior);
        }
        // A la derecha del agujero:
        if((yAgujero + 1) < x){
            Habitacion HabitacionDerecha = getHabitacion(xAgujero, yAgujero + 1);
            Habitacions.add(HabitacionDerecha);
        }
        // A la izquierda del agujero:
        if((yAgujero - 1) >= 0){
            Habitacion HabitacionIzquierda = getHabitacion(xAgujero, yAgujero - 1);
            Habitacions.add(HabitacionIzquierda);
        }
        for(int i = 0; i < Habitacions.size(); i++){
            Habitacion Habitacion = Habitacions.get(i);
            int xHabitacion = Habitacion.getX(), yHabitacion = Habitacion.getY();
            if(habitacionLibreParaHedorOBrisa(xHabitacion,yHabitacion)){
                Habitacion.setHayBrisa(true);
            }
        }
    }

    /**
     * Método que se encarga de colocar la condición de hedor en las casillas adyacentes a dónde se encuentra el Wumpus.
     * @param xAgujero int, que representa la coordenada en el eje horizontal del Wumpus.
     * @param yAgujero int, que representa la coordenada en el eje vertical del Wumpus.
     */
    public void prepararHedor(int xWumpus, int yWumpus){
        ArrayList<Habitacion> Habitacions = new ArrayList<>();
        // Arriba del Wumpus:
        if((xWumpus - 1) >= 0){
            Habitacion HabitacionSuperior = getHabitacion(xWumpus - 1, yWumpus);
            Habitacions.add(HabitacionSuperior);
        }
        // Abajo del Wumpus:
        if((xWumpus + 1) < x){
            Habitacion HabitacionInferior = getHabitacion(xWumpus + 1, yWumpus);
            Habitacions.add(HabitacionInferior);
        }
        // A la derecha del Wumpus:
        if((yWumpus + 1) < x){
            Habitacion HabitacionDerecha = getHabitacion(xWumpus, yWumpus + 1);
            Habitacions.add(HabitacionDerecha);
        }
        // A la izquierda del Wumpus:
        if((yWumpus - 1) >= 0){
            Habitacion HabitacionIzquierda = getHabitacion(xWumpus, yWumpus - 1);
            Habitacions.add(HabitacionIzquierda);
        }
        for(int i = 0; i < Habitacions.size(); i++){
            Habitacion Habitacion = Habitacions.get(i);
            int xHabitacion = Habitacion.getX(), yHabitacion = Habitacion.getY();
            if(habitacionLibreParaHedorOBrisa(xHabitacion,yHabitacion)){
                Habitacion.setHayHedor(true);
            }
        }
    }

    /**
     * Método que indica si la habitación, cuyas coordenadas se pasan por parámetro, está libre del jugador, 
     * del Wumpus, de los agujeros y del oro.
     * @param xHab int, que representa la coordenada vertical de la habitación.
     * @param yHab int, que representa la coordenada horizontal de la habitación.
     * @return boolean, la condición de si la habitación está libre. 
     */
    public boolean habitacionLibre(int xHab, int yHab){
        Habitacion Habitacion = getHabitacion(xHab,yHab);
        return !(xHab == 0 && yHab == 0) && !Habitacion.getHayWumpus() && !Habitacion.getHayAgujero() && !Habitacion.getHayOro();
    }

    /**
     * Método que indica si la habitación, cuyas coordenadas se pasan por parámetro, está debidamente preparada para poder
     * colocar la condición de hedor y/o brisa.
     * @param xHab int, que representa la coordenada vertical de la habitación.
     * @param yHab int, que representa la coordenada horizontal de la habitación.
     * @return boolean, la condición de si la habitación está libre para colocar el hedor y/o la brisa. 
     */
    public boolean habitacionLibreParaHedorOBrisa(int xHab, int yHab){
        Habitacion Habitacion = getHabitacion(xHab,yHab);
        return !Habitacion.getHayWumpus() && !Habitacion.getHayAgujero();
    }

    /**
     * Método que crea un mensaje informativo de la situación estratégica de la partida.
     * return String, el mensaje con la información sobre la situación de la partida.
     */
    public String mostrarInformacion(){
        String mensaje = "";
        Habitacion HabitacionActual = getHabitacion(jugador.getX(), jugador.getY());
        if(HabitacionActual.getHayWumpus()){
            mensaje = "El jugador se ha topado con el Wumpus y ha sido eliminado. ¡Mejor suerte la próxima vez!"; 
            jugador.setEstaMuerto(true);
            jugador.setPartidaGanada(true);
        } else if(HabitacionActual.getHayAgujero()){
            mensaje = "El jugador ha caído por un agujero y ha sido eliminado. ¡Mejor suerte la próxima vez!"; 
            jugador.setEstaMuerto(true);
            jugador.setPartidaGanada(true);
        }
        if(!jugador.getEstaMuerto() && HabitacionActual.getHayHedor()){
            if(!mensaje.equals("")){
                mensaje += "\n";
            }
            mensaje += "Se percibe el hedor del Wumpus. ¡Cuidado!"; 
        }
        if(!jugador.getEstaMuerto() && HabitacionActual.getHayBrisa()){
            if(!mensaje.equals("")){
                mensaje += "\n";
            }
            mensaje += "Se percibe la brisa de un agujero. ¡Cuidado!"; 
        }
        if(!jugador.getEstaMuerto() && HabitacionActual.getHayOro()){
            if(!mensaje.equals("")){
                mensaje += "\n";
            }
            mensaje += "¡Has conseguido el oro! ¡Hora de buscar la salida!"; 
            jugador.setTieneOro(true);
        } 
        if(!jugador.getEstaMuerto() && !HabitacionActual.getHayHedor() && !HabitacionActual.getHayBrisa() && !HabitacionActual.getHayOro()){
            if(!mensaje.equals("")){
                mensaje += "\n";
            }
            mensaje += "De momento todo está tranquilo...";
        }
        if(!jugador.getEstaMuerto() && jugador.getX() == 0 && jugador.getY() == 0 && jugador.getTieneOro()){
            mensaje = "¡ENHORABUENA! ¡HAS CONSEGUIDO ESCAPAR CON EL ORO!";
            jugador.setEstaMuerto(true);
            jugador.setPartidaGanada(true);
        }
        return mensaje;
    }

    /**
     * Método que lleva a cabo el disparo efectuado por el jugador, modificando las habitaciones del tablero
     * por sus consecuencias derivadas.
     * @param xJugador int, que representa la coordenada del eje horizontal de la posición jugador.
     * @param yJugador int, que representa la coordenada del eje vertical de la posición jugador.
     * @param direccion int, que representa la dirección hacia la cuál irá la flecha disparada.
     * @return boolean, la condición de si el Wumpus ha sido alcanzado por una flecha.
     */
    public boolean disparo(int xJugador, int yJugador, int direccion){
        boolean wumpusMuerto = false;
        boolean case0 = false;
        if(jugador.getFlechas() != 0){
            jugador.setFlechas(jugador.getFlechas() -1);
            switch(direccion){
                case 0: case0 = true;
                break;
                case 1:
                for(int i = xJugador; i > 0 && !wumpusMuerto; i--){
                    Habitacion Habitacion = getHabitacion((i-1), yJugador);
                    if(Habitacion.getHayWumpus()){
                        System.out.println("¡HAS MATADO AL WUMPUS!");
                        eliminarHedor(Habitacion.getX(), Habitacion.getX());
                        Habitacion.setHayWumpus(false);
                        wumpusMuerto = true;
                    }
                }
                break;
                case 2:
                for(int i = xJugador; i < (x-1) && !wumpusMuerto; i++){
                    Habitacion Habitacion = getHabitacion((i+1), yJugador);
                    if(Habitacion.getHayWumpus()){
                        System.out.println("¡HAS MATADO AL WUMPUS!");
                        eliminarHedor(Habitacion.getX(), Habitacion.getX());
                        Habitacion.setHayWumpus(false);
                        wumpusMuerto = true;
                    }
                }
                break;
                case 3: 
                for(int i = yJugador; i < (x-1) && !wumpusMuerto; i++){
                    Habitacion Habitacion = getHabitacion(xJugador, (i+1));
                    if(Habitacion.getHayWumpus()){
                        System.out.println("¡HAS MATADO AL WUMPUS!");
                        eliminarHedor(Habitacion.getX(), Habitacion.getX());
                        Habitacion.setHayWumpus(false);
                        wumpusMuerto = true;
                    }
                }
                break;
                case 4:
                for(int i = yJugador; i > 0 && !wumpusMuerto; i--){
                    Habitacion Habitacion = getHabitacion(xJugador, (i-1));
                    if(Habitacion.getHayWumpus()){
                        System.out.println("¡HAS MATADO AL WUMPUS!");
                        eliminarHedor(Habitacion.getX(), Habitacion.getX());
                        Habitacion.setHayWumpus(false);
                        wumpusMuerto = true;
                    }
                }
                break;
            }
            if(!wumpusMuerto && !case0){
                System.out.println("La fecha ha topado con el muro. ¡El Wumpus sigue vivo!");
            }
        } else {
            System.out.println("El jugador ya no puede disparar más porque ya no tiene más flechas.");
        }
        return wumpusMuerto;
    }

    /**
     * Método que elimina el hedor de las casillas adyacentes a dónde se encontraba el Wumpus antes de ser abatido
     * @param xWumpus int, que representa la coordenada del eje horizontal del Wumpus.
     * @param yWumpus int, que representa la coordenada del eje vertical del Wumpus.
     */
    public void eliminarHedor(int xWumpus, int yWumpus){
        for(int i = 0; i < x; i++){
            for(int j = 0; j < x; j++){
                Habitacion Habitacion = getHabitacion(i,j);
                if(Habitacion.getHayHedor()){
                    Habitacion.setHayHedor(false);
                }
            }
        }
    }
}
