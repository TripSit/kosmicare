package eddiecurtis.github.com.kosmicare;

/**
 * Created by eddie on 23/07/15.
 */
public class FormData {

    static final String DELIM = ",";

    public static FormData fromCsvString(String csv) {
        FormData data = new FormData();
        String[] values = csv.split(DELIM, -1); // -1 to include empty values
        data.setVisitorId(Integer.parseInt(values[0]));
        data.setDay(values[1]);
        data.setMonth(values[2]);
        data.setYear(values[3]);
        data.setEvent(values[4]);
        data.setFacilitator(values[5]);
        data.setVisitor(values[6]);
        data.setAge(values[7]);
        data.setGender(values[8]);
        data.setArrival(values[9]);
        data.setReason(values[10]);
        data.setReasonOtherText(values[11]);
        data.setEffects(values[12]);
        data.setConditionTerrified(Boolean.parseBoolean(values[13]));
        data.setConditionDisturbed(Boolean.parseBoolean(values[14]));
        data.setConditionPhysicallyAgitated(Boolean.parseBoolean(values[15]));
        data.setConditionPhysicallyCalm(Boolean.parseBoolean(values[16]));
        data.setConditionVerbal(Boolean.parseBoolean(values[17]));
        data.setConditionNonVerbal(Boolean.parseBoolean(values[18]));
        data.setConditionCalm(Boolean.parseBoolean(values[19]));
        data.setConditionUpset(Boolean.parseBoolean(values[20]));
        data.setConditionParanoid(Boolean.parseBoolean(values[21]));
        data.setConditionEgoLoss(Boolean.parseBoolean(values[22]));
        data.setSymptoms(values[23]);
        data.setIssues(values[24]);
        data.setTechniqueEffectiveListening(Boolean.parseBoolean(values[25]));
        data.setTechniqueEffectiveTalking(Boolean.parseBoolean(values[26]));
        data.setTechniqueEffectiveHugging(Boolean.parseBoolean(values[27]));
        data.setTechniqueEffectiveSitting(Boolean.parseBoolean(values[28]));
        data.setTechniqueEffectiveHoldingHands(Boolean.parseBoolean(values[29]));
        data.setTechniqueEffectiveCreativeTherapy(Boolean.parseBoolean(values[30]));
        data.setTechniqueEffectiveLeftAlone(Boolean.parseBoolean(values[31]));
        data.setTechniqueEffectiveOther(Boolean.parseBoolean(values[32]));
        data.setTechniqueIneffectiveListening(Boolean.parseBoolean(values[33]));
        data.setTechniqueIneffectiveTalking(Boolean.parseBoolean(values[34]));
        data.setTechniqueIneffectiveHugging(Boolean.parseBoolean(values[35]));
        data.setTechniqueIneffectiveSitting(Boolean.parseBoolean(values[36]));
        data.setTechniqueIneffectiveHoldingHands(Boolean.parseBoolean(values[37]));
        data.setTechniqueIneffectiveCreativeTherapy(Boolean.parseBoolean(values[38]));
        data.setTechniqueIneffectiveLeftAlone(Boolean.parseBoolean(values[39]));
        data.setTechniqueIneffectiveOther(Boolean.parseBoolean(values[40]));
        data.setTechniqueOtherText(values[41]);
        data.setArrivalHour(values[42]);
        data.setArrivalMinute(values[43]);
        data.setLeavingHour(values[44]);
        data.setLeavingMinute(values[45]);
        data.setDuration(values[46]);
        data.setFeedback(values[47]);
        data.setLeavingCondition(values[48]);
        data.setIssuesResolution(values[49]);
        data.setConclusion(values[50]);
        return data;
    }

    public String toCsvString() {
        StringBuilder sb = new StringBuilder();
        sb.append(visitorId)
                .append(DELIM)
                .append(day)
                .append(DELIM)
                .append(month)
                .append(DELIM)
                .append(year)
                .append(DELIM)
                .append(event)
                .append(DELIM)
                .append(facilitator)
                .append(DELIM)
                .append(visitor)
                .append(DELIM)
                .append(age)
                .append(DELIM)
                .append(gender)
                .append(DELIM)
                .append(arrival)
                .append(DELIM)
                .append(reason)
                .append(DELIM)
                .append(reasonOtherText)
                .append(DELIM)
                .append(effects)
                .append(DELIM)
                .append(conditionTerrified)
                .append(DELIM)
                .append(conditionDisturbed)
                .append(DELIM)
                .append(conditionPhysicallyAgitated)
                .append(DELIM)
                .append(conditionPhysicallyCalm)
                .append(DELIM)
                .append(conditionVerbal)
                .append(DELIM)
                .append(conditionNonVerbal)
                .append(DELIM)
                .append(conditionCalm)
                .append(DELIM)
                .append(conditionUpset)
                .append(DELIM)
                .append(conditionParanoid)
                .append(DELIM)
                .append(conditionEgoLoss)
                .append(DELIM)
                .append(symptoms)
                .append(DELIM)
                .append(issues)
                .append(DELIM)
                .append(techniqueEffectiveListening)
                .append(DELIM)
                .append(techniqueEffectiveTalking)
                .append(DELIM)
                .append(techniqueEffectiveHugging)
                .append(DELIM)
                .append(techniqueEffectiveSitting)
                .append(DELIM)
                .append(techniqueEffectiveHoldingHands)
                .append(DELIM)
                .append(techniqueEffectiveCreativeTherapy)
                .append(DELIM)
                .append(techniqueEffectiveLeftAlone)
                .append(DELIM)
                .append(techniqueEffectiveOther)
                .append(DELIM)
                .append(techniqueIneffectiveListening)
                .append(DELIM)
                .append(techniqueIneffectiveTalking)
                .append(DELIM)
                .append(techniqueIneffectiveHugging)
                .append(DELIM)
                .append(techniqueIneffectiveSitting)
                .append(DELIM)
                .append(techniqueIneffectiveHoldingHands)
                .append(DELIM)
                .append(techniqueIneffectiveCreativeTherapy)
                .append(DELIM)
                .append(techniqueIneffectiveLeftAlone)
                .append(DELIM)
                .append(techniqueIneffectiveOther)
                .append(DELIM)
                .append(techniqueOtherText)
                .append(DELIM)
                .append(arrivalHour)
                .append(DELIM)
                .append(arrivalMinute)
                .append(DELIM)
                .append(leavingHour)
                .append(DELIM)
                .append(leavingMinute)
                .append(DELIM)
                .append(duration)
                .append(DELIM)
                .append(feedback)
                .append(DELIM)
                .append(leavingCondition)
                .append(DELIM)
                .append(issuesResolution)
                .append(DELIM)
                .append(conclusion);
        return sb.toString();
    }

    private String stripDelim(String input) {
        return input.replaceAll(DELIM, "");
    }

    private int visitorId = 0;
    private String day= "";
    private String month= "";
    private String year= "";
    private String event= "";
    private String facilitator= "";
    private String visitor= "";
    private String age= "";
    private String gender= "";
    private String arrival= "";
    private String reason= "";
    private String reasonOtherText= "";
    private String effects= "";
    private boolean conditionTerrified= false;
    private boolean conditionDisturbed= false;
    private boolean conditionPhysicallyAgitated= false;
    private boolean conditionPhysicallyCalm= false;
    private boolean conditionVerbal= false;
    private boolean conditionNonVerbal= false;
    private boolean conditionCalm= false;
    private boolean conditionUpset = false;
    private boolean conditionParanoid = false;
    private boolean conditionEgoLoss = false;
    private String symptoms= "";
    private String issues= "";
    private boolean techniqueEffectiveListening= false;
    private boolean techniqueEffectiveTalking= false;
    private boolean techniqueEffectiveHugging= false;
    private boolean techniqueEffectiveSitting= false;
    private boolean techniqueEffectiveHoldingHands= false;
    private boolean techniqueEffectiveCreativeTherapy= false;
    private boolean techniqueEffectiveLeftAlone= false;
    private boolean techniqueEffectiveOther= false;
    private boolean techniqueIneffectiveListening= false;
    private boolean techniqueIneffectiveTalking= false;
    private boolean techniqueIneffectiveHugging= false;
    private boolean techniqueIneffectiveSitting= false;
    private boolean techniqueIneffectiveHoldingHands= false;
    private boolean techniqueIneffectiveCreativeTherapy= false;
    private boolean techniqueIneffectiveLeftAlone= false;
    private boolean techniqueIneffectiveOther= false;
    private String techniqueOtherText= "";
    private String arrivalHour= "";
    private String arrivalMinute= "";
    private String leavingHour= "";
    private String leavingMinute= "";
    private String duration= "";
    private String feedback= "";
    private String leavingCondition= "";
    private String issuesResolution= "";
    private String conclusion= "";

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = stripDelim(day);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = stripDelim(month);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = stripDelim(year);
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = stripDelim(event);
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = stripDelim(facilitator);
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = stripDelim(visitor);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = stripDelim(age);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = stripDelim(gender);
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = stripDelim(arrival);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = stripDelim(reason);
    }

    public String getReasonOtherText() {
        return reasonOtherText;
    }

    public void setReasonOtherText(String reasonOtherText) {
        this.reasonOtherText = stripDelim(reasonOtherText);
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = stripDelim(effects);
    }

    public boolean isConditionTerrified() {
        return conditionTerrified;
    }

    public void setConditionTerrified(boolean conditionTerrified) {
        this.conditionTerrified = conditionTerrified;
    }

    public boolean isConditionDisturbed() {
        return conditionDisturbed;
    }

    public void setConditionDisturbed(boolean conditionDisturbed) {
        this.conditionDisturbed = conditionDisturbed;
    }

    public boolean isConditionPhysicallyAgitated() {
        return conditionPhysicallyAgitated;
    }

    public void setConditionPhysicallyAgitated(boolean conditionPhysicallyAgitated) {
        this.conditionPhysicallyAgitated = conditionPhysicallyAgitated;
    }

    public boolean isConditionPhysicallyCalm() {
        return conditionPhysicallyCalm;
    }

    public void setConditionPhysicallyCalm(boolean conditionPhysicallyCalm) {
        this.conditionPhysicallyCalm = conditionPhysicallyCalm;
    }

    public boolean isConditionVerbal() {
        return conditionVerbal;
    }

    public void setConditionVerbal(boolean conditionVerbal) {
        this.conditionVerbal = conditionVerbal;
    }

    public boolean isConditionNonVerbal() {
        return conditionNonVerbal;
    }

    public void setConditionNonVerbal(boolean conditionNonVerbal) {
        this.conditionNonVerbal = conditionNonVerbal;
    }

    public boolean isConditionCalm() {
        return conditionCalm;
    }

    public void setConditionCalm(boolean conditionCalm) {
        this.conditionCalm = conditionCalm;
    }

    public boolean isConditionUpset() {
        return conditionUpset;
    }

    public void setConditionUpset(boolean conditionUpset) {
        this.conditionUpset = conditionUpset;
    }

    public boolean isConditionParanoid() {
        return conditionParanoid;
    }

    public void setConditionParanoid(boolean conditionParanoid) {
        this.conditionParanoid = conditionParanoid;
    }

    public boolean isConditionEgoLoss() {
        return conditionEgoLoss;
    }

    public void setConditionEgoLoss(boolean conditionEgoLoss) {
        this.conditionEgoLoss = conditionEgoLoss;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = stripDelim(symptoms);
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public boolean isTechniqueEffectiveListening() {
        return techniqueEffectiveListening;
    }

    public void setTechniqueEffectiveListening(boolean techniqueEffectiveListening) {
        this.techniqueEffectiveListening = techniqueEffectiveListening;
    }

    public boolean isTechniqueEffectiveTalking() {
        return techniqueEffectiveTalking;
    }

    public void setTechniqueEffectiveTalking(boolean techniqueEffectiveTalking) {
        this.techniqueEffectiveTalking = techniqueEffectiveTalking;
    }

    public boolean isTechniqueEffectiveHugging() {
        return techniqueEffectiveHugging;
    }

    public void setTechniqueEffectiveHugging(boolean techniqueEffectiveHugging) {
        this.techniqueEffectiveHugging = techniqueEffectiveHugging;
    }

    public boolean isTechniqueEffectiveSitting() {
        return techniqueEffectiveSitting;
    }

    public void setTechniqueEffectiveSitting(boolean techniqueEffectiveSitting) {
        this.techniqueEffectiveSitting = techniqueEffectiveSitting;
    }

    public boolean isTechniqueEffectiveHoldingHands() {
        return techniqueEffectiveHoldingHands;
    }

    public void setTechniqueEffectiveHoldingHands(boolean techniqueEffectiveHoldingHands) {
        this.techniqueEffectiveHoldingHands = techniqueEffectiveHoldingHands;
    }

    public boolean isTechniqueEffectiveCreativeTherapy() {
        return techniqueEffectiveCreativeTherapy;
    }

    public void setTechniqueEffectiveCreativeTherapy(boolean techniqueEffectiveCreativeTherapy) {
        this.techniqueEffectiveCreativeTherapy = techniqueEffectiveCreativeTherapy;
    }

    public boolean isTechniqueEffectiveLeftAlone() {
        return techniqueEffectiveLeftAlone;
    }

    public void setTechniqueEffectiveLeftAlone(boolean techniqueEffectiveLeftAlone) {
        this.techniqueEffectiveLeftAlone = techniqueEffectiveLeftAlone;
    }

    public boolean isTechniqueEffectiveOther() {
        return techniqueEffectiveOther;
    }

    public void setTechniqueEffectiveOther(boolean techniqueEffectiveOther) {
        this.techniqueEffectiveOther = techniqueEffectiveOther;
    }

    public boolean isTechniqueIneffectiveListening() {
        return techniqueIneffectiveListening;
    }

    public void setTechniqueIneffectiveListening(boolean techniqueIneffectiveListening) {
        this.techniqueIneffectiveListening = techniqueIneffectiveListening;
    }

    public boolean isTechniqueIneffectiveTalking() {
        return techniqueIneffectiveTalking;
    }

    public void setTechniqueIneffectiveTalking(boolean techniqueIneffectiveTalking) {
        this.techniqueIneffectiveTalking = techniqueIneffectiveTalking;
    }

    public boolean isTechniqueIneffectiveHugging() {
        return techniqueIneffectiveHugging;
    }

    public void setTechniqueIneffectiveHugging(boolean techniqueIneffectiveHugging) {
        this.techniqueIneffectiveHugging = techniqueIneffectiveHugging;
    }

    public boolean isTechniqueIneffectiveSitting() {
        return techniqueIneffectiveSitting;
    }

    public void setTechniqueIneffectiveSitting(boolean techniqueIneffectiveSitting) {
        this.techniqueIneffectiveSitting = techniqueIneffectiveSitting;
    }

    public boolean isTechniqueIneffectiveHoldingHands() {
        return techniqueIneffectiveHoldingHands;
    }

    public void setTechniqueIneffectiveHoldingHands(boolean techniqueIneffectiveHoldingHands) {
        this.techniqueIneffectiveHoldingHands = techniqueIneffectiveHoldingHands;
    }

    public boolean isTechniqueIneffectiveCreativeTherapy() {
        return techniqueIneffectiveCreativeTherapy;
    }

    public void setTechniqueIneffectiveCreativeTherapy(boolean techniqueIneffectiveCreativeTherapy) {
        this.techniqueIneffectiveCreativeTherapy = techniqueIneffectiveCreativeTherapy;
    }

    public boolean isTechniqueIneffectiveLeftAlone() {
        return techniqueIneffectiveLeftAlone;
    }

    public void setTechniqueIneffectiveLeftAlone(boolean techniqueIneffectiveLeftAlone) {
        this.techniqueIneffectiveLeftAlone = techniqueIneffectiveLeftAlone;
    }

    public boolean isTechniqueIneffectiveOther() {
        return techniqueIneffectiveOther;
    }

    public void setTechniqueIneffectiveOther(boolean techniqueIneffectiveOther) {
        this.techniqueIneffectiveOther = techniqueIneffectiveOther;
    }

    public String getTechniqueOtherText() {
        return techniqueOtherText;
    }

    public void setTechniqueOtherText(String techniqueOtherText) {
        this.techniqueOtherText = stripDelim(techniqueOtherText);
    }

    public String getArrivalHour() {
        return arrivalHour;
    }

    public void setArrivalHour(String arrivalHour) {
        this.arrivalHour = arrivalHour;
    }

    public String getArrivalMinute() {
        return arrivalMinute;
    }

    public void setArrivalMinute(String arrivalMinute) {
        this.arrivalMinute = arrivalMinute;
    }

    public String getLeavingHour() {
        return leavingHour;
    }

    public void setLeavingHour(String leavingHour) {
        this.leavingHour = stripDelim(leavingHour);
    }

    public String getLeavingMinute() {
        return leavingMinute;
    }

    public void setLeavingMinute(String leavingMinute) {
        this.leavingMinute = stripDelim(leavingMinute);
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = stripDelim(duration);
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = stripDelim(feedback);
    }

    public String getLeavingCondition() {
        return leavingCondition;
    }

    public void setLeavingCondition(String leavingCondition) {
        this.leavingCondition = stripDelim(leavingCondition);
    }

    public String getIssuesResolution() {
        return issuesResolution;
    }

    public void setIssuesResolution(String issuesResolution) {
        this.issuesResolution = stripDelim(issuesResolution);
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = stripDelim(conclusion);
    }
}
