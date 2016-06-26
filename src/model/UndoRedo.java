package model;

import java.util.ArrayList;
import java.util.List;

import model_interface.ISchedulesModel;
import model_interface.IUndoRedo;

public class UndoRedo implements IUndoRedo {
    private final List<ISchedulesModel> undoList;
    private ISchedulesModel model;
    private int index;

    public UndoRedo() {
        this.undoList = new ArrayList<>();
        this.model = new SchedulesModel();
        this.index = 0;
    }
    
    @Override
    public ISchedulesModel setModel(final ISchedulesModel model) {
        if (model == null) {
            return null;
        }
        return this.model = model;
    }
    
    @Override
    public void addUndoState() {
        this.undoList.add(this.model);
        this.index++;
    }

    @Override
    public ISchedulesModel undo() {
        if (this.index==0) {
            return this.model;
        }
        if (this.undoList.size() == this.index) {
            this.addUndoState();
            this.index--;
        }
        this.index--;
        this.model.copy(this.undoList.get(index));
//        this.model.setClassRoomList(this.undoList.get(index).getClassroomsList());
//        this.model.setLessonList(this.undoList.get(index).getLessons(null, null, null, null, null, null, null, null));
//        this.model.setProfessorsList(this.undoList.get(index).getProfessorsList());
//        this.model.setTeachingList(this.undoList.get(index).getTeachingsList());
        return this.model;
    }

    @Override
    public ISchedulesModel redo() {
        if (this.undoList.size() == this.index) {
            return null;
        }
        this.index++;
        this.model.copy(this.undoList.get(index));
//      this.model.setClassRoomList(this.undoList.get(index).getClassroomsList());
//      this.model.setLessonList(this.undoList.get(index).getLessons(null, null, null, null, null, null, null, null));
//      this.model.setProfessorsList(this.undoList.get(index).getProfessorsList());
//      this.model.setTeachingList(this.undoList.get(index).getTeachingsList());
        return this.model;
    }

}
