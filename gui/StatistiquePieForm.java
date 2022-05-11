package com.mycompany.myapp.gui;






import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Chédy
 */

public class StatistiquePieForm extends Form {
  
        private double salairec = 2000 ;   
        private double salaireo = 600;
    private boolean drawOnMutableImage;
   
    
    Form current;
Form form;
        public StatistiquePieForm(Resources res, Form previous)  {
        super("Newsfeed", BoxLayout.y());
            current= this;
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
        getTitleArea().setUIID("Container");
        setTitle("Acceuill");
        getContentPane().setScrollVisible(false);
        
        
        
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        // addTab(swipe, res.getImage("back-logo.jpeg"), spacer1, "Bienvenue");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
                

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
      //  rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        refreshTheme();
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        
        RadioButton all = RadioButton.createToggle("Feedback", barGroup);
       
        all.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Categorie Reclamation", barGroup);
        popular.setUIID("SelectBar");
        RadioButton feedback = RadioButton.createToggle("Feedback", barGroup);
        feedback.setUIID("SelectBar");
        RadioButton profile = RadioButton.createToggle("Statistique", barGroup);
        profile.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, popular,profile),
                FlowLayout.encloseBottom(arrow)
        ));
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
           
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(popular, arrow);
                all.addActionListener((e)->{
        });

                 popular.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(profile, arrow);
        });
        bindButtonSelection(profile, arrow);
                profile.addActionListener((e)->{
                  new StatistiquePieForm(res,previous).show();
                  
                  
        });
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
    
        createPieChartForm();
      
        
        }
    
    
    
    
    
     private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
   /* private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    */
  
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
     public DefaultRenderer buildCatRenderer (int []colors) {
   DefaultRenderer renderer = new DefaultRenderer();
   renderer.setLabelsTextSize(15);
   renderer.setLegendTextSize(15);
   renderer.setMargins(new int[]{20,30,15,0});
   for (int color : colors)
   {
     SimpleSeriesRenderer simpleseriesrenderer = new SimpleSeriesRenderer();
     simpleseriesrenderer.setColor(color);
     renderer.addSeriesRenderer(simpleseriesrenderer);
   }
   return renderer;
   }
   
   public void createPieChartForm() {
   double totale = salaireo + salairec ;
   double pourcentagef = (salaireo*100)/totale;
   double pourcentager = (salairec*100)/totale;
   int[]colors = new int[]{0xf4b342,0x52b29a} ;
   DefaultRenderer renderer = buildCatRenderer(colors);
   renderer.setLabelsColor(0x000000);
   renderer.setZoomButtonsVisible(true);
   renderer.setZoomEnabled(true);
   renderer.setChartTitleTextSize(20);
   renderer.setDisplayValues(true);
   renderer.setShowLabels(true);
   SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
   r.setHighlighted(true);
   
   PieChart chart = new PieChart(buildDataset("title",Math.round(pourcentagef),Math.round(pourcentager)), renderer);
       ChartComponent c = new ChartComponent(chart);
       String []messages = {"statistique feedback % reclamation"};
       SpanLabel message = new SpanLabel (messages[0],"welcome message");
       Container cnt = BorderLayout.center(message);
       cnt.setUIID("container");
       add(cnt);
       add(c);
   } 

    private CategorySeries buildDataset(String title, double pourcentagef, double pourcentager) {
        CategorySeries serie = new CategorySeries(title);
        serie.add("salaire des chefs",pourcentager);
        serie.add("salaire des ouvriers",pourcentagef);
        return serie;
    }
    
    
}