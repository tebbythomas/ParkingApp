package cse5236.parkingapp;

/**
 * Created by Gregg on 11/10/2014.
 */
public class Location {

    /* Private Variables */
    int id;
    String name;
    String coord;
    String description;
    double price;
    String type;
    String day1s;
    String day1e;
    String day2s;
    String day2e;
    String day3s;
    String day3e;
    String day4s;
    String day4e;
    String day5s;
    String day5e;
    String day6s;
    String day6e;
    String day7s;
    String day7e;
    String restriction;

    /* Empty Constructor */
    public Location(){

    }

    /* Parameterized Constructor */
    public Location (int id, String name, String coord, String description, double price, String type, String day1s, String day1e, String day2s, String day2e, String day3s, String day3e, String day4s, String day4e, String day5s, String day5e, String day6s, String day6e, String day7s, String day7e, String restriction) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.description = description;
        this.price = price;
        this.type = type;
        this.day1s = day1s;
        this.day1e = day1e;
        this.day2s = day2s;
        this.day2e = day2e;
        this.day3s = day3s;
        this.day3e = day3e;
        this.day4s = day4s;
        this.day4e = day4e;
        this.day5s = day5s;
        this.day5e = day5e;
        this.day6s = day6s;
        this.day6e = day6e;
        this.day7s = day7s;
        this.day7e = day7e;
        this.restriction = restriction;
    }
    public String getDay4e() {
        return day4e;
    }

    public void setDay4e(String day4e) {
        this.day4e = day4e;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDay1s() {
        return day1s;
    }

    public void setDay1s(String day1s) {
        this.day1s = day1s;
    }

    public String getDay1e() {
        return day1e;
    }

    public void setDay1e(String day1e) {
        this.day1e = day1e;
    }

    public String getDay2s() {
        return day2s;
    }

    public void setDay2s(String day2s) {
        this.day2s = day2s;
    }

    public String getDay2e() {
        return day2e;
    }

    public void setDay2e(String day2e) {
        this.day2e = day2e;
    }

    public String getDay3s() {
        return day3s;
    }

    public void setDay3s(String day3s) {
        this.day3s = day3s;
    }

    public String getDay3e() {
        return day3e;
    }

    public void setDay3e(String day3e) {
        this.day3e = day3e;
    }

    public String getDay4s() {
        return day4s;
    }

    public void setDay4s(String day4s) {
        this.day4s = day4s;
    }

    public String getDay5s() {
        return day5s;
    }

    public void setDay5s(String day5s) {
        this.day5s = day5s;
    }

    public String getDay5e() {
        return day5e;
    }

    public void setDay5e(String day5e) {
        this.day5e = day5e;
    }

    public String getDay6s() {
        return day6s;
    }

    public void setDay6s(String day6s) {
        this.day6s = day6s;
    }

    public String getDay6e() {
        return day6e;
    }

    public void setDay6e(String day6e) {
        this.day6e = day6e;
    }

    public String getDay7s() {
        return day7s;
    }

    public void setDay7s(String day7s) {
        this.day7s = day7s;
    }

    public String getDay7e() {
        return day7e;
    }

    public void setDay7e(String day7e) {
        this.day7e = day7e;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

   /* public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCoord(){
        return this.coord;
    }

    public void setCoord(String coord){
        this.coord = coord;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getDay1s(){
        return this.day1s;
    }

    public void setDay1s(String day1s){
        this.day1s = day1s;
    }

    public String getDay1e(){
        return this.day1e;
    }

    public void setDay1e(String day1e){
        this.day1e = day1e;
    }

    public String getDay2s(){
        return this.day2s;
    }

    public void setDay2s(String day2s){
        this.day1s = day2s;
    }

    public String getDay2e(){
        return this.day2e;
    }

    public void setDay2e(String day2e){
        this.day1e = day2e;
    }

    public String getDay3s(){
        return this.day3s;
    }

    public void setDay3s(String day3s){
        this.day1s = day3s;
    }

    public String getDay3e(){
        return this.day3e;
    }

    public void setDay3e(String day3e){
        this.day1e = day3e;
    }

    public String getDay4s(){
        return this.day1s;
    }

    public void setDay4s(String day4s){
        this.day1s = day4s;
    }

    public String getDay4e(){
        return this.day4e;
    }

    public void setDay4e(String day4e){
        this.day1e = day4e;
    }

    public String getDay5s(){
        return this.day5s;
    }

    public void setDay5s(String day5s){
        this.day1s = day5s;
    }

    public String getDay5e(){
        return this.day5e;
    }

    public void setDay5e(String day5e){
        this.day1e = day5e;
    }

    public String getDay6s(){
        return this.day6s;
    }

    public void setDay6s(String day6s){
        this.day1s = day6s;
    }

    public String getDay6e(){
        return this.day6e;
    }

    public void setDay6e(String day6e){
        this.day1e = day6e;
    }

    public String getDay7s(){
        return this.day7s;
    }

    public void setDay7s(String day7s){
        this.day1s = day7s;
    }

    public String getDay7e(){
        return this.day7e;
    }

    public void setDay7e(String day7e){
        this.day1e = day7e;
    }

    public String getRestriction(){
        return this.restriction;
    }

    public void setRestriction(String restriction){
        this.restriction = restriction;
    }*/
}
