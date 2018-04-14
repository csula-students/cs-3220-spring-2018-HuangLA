package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json
 * {
 *   "events": [
 *     { "id": 0, "name": "event-1", "description": "..." }
 *   ],
 *   "generators": [
 *     { "id": 0, "name": "generator-1", "description": "..." }
 *   ]
 * }
 * ```
 */
public class EventsDAOImpl implements EventsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "events";

	public EventsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		// TODO: read a list of events from context
		List<Event> list= new ArrayList<Event>();
		

		
		Object data = context.getAttribute(CONTEXT_NAME);
		if (data == null) {
			return list;
		} else {
			return (List<Event>) data;
		}

	}

	@Override
	public Optional<Event> getById(int id) {
		// TODO: get a certain event given its id from context (see getAll() on
		// getting a list first and get a certain one from the list)
		Optional<Event> actualEvent = Optional.empty();
		List<Event> list = (List<Event>) context.getAttribute(CONTEXT_NAME);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				actualEvent = Optional.of(list.get(i));
			}
		}

		return actualEvent;
		//return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		// TODO: set a certain event given id to be different from context
		List<Event> newList = getAll();
		for (int i = 0; i < newList.size(); i++) {
			if (newList.get(i).getId() == id) {
				newList.get(i).setName(event.getName());
				newList.get(i).setDescription(event.getDescription());
				newList.get(i).setTriggerAt(event.getTriggerAt());
			}
		}
		

		context.setAttribute(CONTEXT_NAME, newList);

	}

	@Override
	public void add(Event event) {
		// TODO: add a new event to the context
		List<Event> newList = getAll();
		newList.add(event);

		context.setAttribute(CONTEXT_NAME, newList);
	}

	@Override
	public void remove(int id) {
		// TODO: remove a single event given id
		List<Event> newList = getAll();
		for (int i = 0; i < newList.size(); i++) {
			if (newList.get(i).getId() == id) {
				newList.remove(i);
			}
		}
		

		context.setAttribute(CONTEXT_NAME, newList);
	
	}
}
