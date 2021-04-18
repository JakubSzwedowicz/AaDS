package Benchmarking;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 18.04.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class Timer {
    private double m_start;
    private double m_end;
    private double m_duration;
    private double m_overallDuration;
    private boolean m_running;

    public Timer() {
        m_start = 0;
        m_end = 0;
        m_duration = 0;
        m_running = false;
    }

    public void start() {
        m_start = System.nanoTime();
        m_running = true;
    }

    public void reset(){
        m_start = 0;
        m_end = 0;
        m_duration = 0;
        m_running = false;
        m_overallDuration = 0;
    }

    public void stop() {
        m_end = System.nanoTime();
        m_running = false;
        m_duration = m_end - m_start;
        m_overallDuration += m_duration;
    }

    public double get_durationInNano() {
        if (!m_running) {
            return m_duration;
        }
        return m_end - m_start;
    }

    public double get_durationInMicro() {
        if (!m_running) {
            return m_duration / 1000;
        }
        return (m_end - m_start) / 1000;
    }

    public double get_durationInMilis() {
        if (!m_running) {
            return m_duration / 1000000;
        }
        return (m_end - m_start) / 1000000;
    }

    public double get_overallDurationInNano() {
        if (!m_running) {
            return m_overallDuration;
        }
        return m_overallDuration + m_end - m_start;
    }

    public double get_overallDurationInMicro() {
        if (!m_running) {
            return m_overallDuration / 1000;
        }
        return (m_overallDuration + m_end - m_start) / 1000;
    }

    public double get_overallDurationInMilis() {
        if (!m_running) {
            return m_overallDuration / 1000000;
        }
        return (m_overallDuration + m_end - m_start) / 1000000;
    }
}
