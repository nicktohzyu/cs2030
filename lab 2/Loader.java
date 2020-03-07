class Loader{

    protected final int id;
    protected final Cruise c;

    public Loader(int id){
        this.id = id;
        this.c = null;
    }

    public Loader(int id, Cruise c){
        this.id = id;
        this.c = c;
    }
    public boolean canServe(Cruise c){
        if(this.c == null || c == null){
            return true;
        }
        return this.c.getServiceCompletionTime() <= c.getArrivalTime();
    }

    public Loader serve(Cruise c){
        if(!this.canServe(c)){
            return null;
        } else {
            return new Loader(this.id, c);
        }
    }

    public String toString(){
        if(c == null){
            return String.format("Loader %d", id);
        } else {
            return String.format("Loader %d serving %s", id, c.toString());
        }
    }
}
