class RecycledLoader extends Loader{

    public RecycledLoader(int id){
        super(id);
    }

    public RecycledLoader(int id, Cruise c){
        super(id, c);
    }

    @Override
        public boolean canServe(Cruise c){
            if(this.c == null || c == null){
                return true;
            }
            return this.c.getServiceCompletionTime() + 60 <= c.getArrivalTime();
        }

    @Override 
        public String toString(){
            if(c == null){
                return String.format("Loader %d", id);
            } else {
                return String.format("Loader %d (recycled) serving %s", id, c.toString());
            }
        }
}

